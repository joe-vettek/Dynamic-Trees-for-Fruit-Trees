package xueluoanping.dtfruittrees.data.lang;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.stream.JsonWriter;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;

import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public abstract class LangHelper extends LanguageProvider {
	private final DataGenerator gen;
	private final ExistingFileHelper helper;


	public LangHelper(DataGenerator gen0, ExistingFileHelper helper, String modid, String locale) {
		super(gen0, modid, locale);
		gen = gen0;
		this.helper = helper;
		this.modid=modid;
		this.locale = locale;
	}

	public void addDebugKey(String key, String value) {
		// add(ModConstant.DebugKey.getRealKey(key), value);
	}

	public void addSpecie(String specieName, String hint) {
		add("species." + modid + '.'+specieName, hint);
	}

	// There is a lot of code here that is redundant, but indispensable. In order to make corrections
	protected abstract void addTranslations();

	private final String locale;
	public final String modid;
	private final Map<String, String> data = new TreeMap<>();

	@Override
	public void run(CachedOutput cache) throws IOException {
		addTranslations();
		if (!data.isEmpty())
			save(cache, data, this.gen.getOutputFolder().resolve("assets/" + modid + "/lang/" + locale + ".json"));
	}

	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

	// Comment out the JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(data) line of code,
	// and specify UTF-8 encoding when creating the BufferedWriter.
	// In this way, strings containing Chinese characters can be processed correctly.
	private void save(CachedOutput cache, Object object, Path target) throws IOException {
		// String data = GSON.toJson(object);
		JsonObject json = new JsonObject();
		for (Map.Entry<String, String> pair : data.entrySet()) {
			json.addProperty(pair.getKey(), pair.getValue());
		}
		// // data = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(data); // Escape unicode after the fact so that it's not double escaped by GSON
		// String hash = DataProvider.SHA1.hashUnencodedChars(data).toString();
		// if (!Objects.equals(cache.getHash(target), hash) || !Files.exists(target)) {
		// 	Files.createDirectories(target.getParent());
		//
		// 	try (BufferedWriter bufferedwriter = Files.newBufferedWriter(target, StandardCharsets.UTF_8)) {
		// 		bufferedwriter.write(data);
		// 	}
		// }
		//
		// cache.putNew(target, hash);

		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha1(), bytearrayoutputstream);
		Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
		JsonWriter jsonwriter = new JsonWriter(writer);
		jsonwriter.setSerializeNulls(false);
		jsonwriter.setIndent("  ");
		GsonHelper.writeValue(jsonwriter, json, KEY_COMPARATOR);
		jsonwriter.close();
		cache.writeIfNeeded(target, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
	}

	public void add(String key, String value) {
		if (data.put(key, value) != null)
			throw new IllegalStateException("Duplicate translation key " + key);
	}
}
