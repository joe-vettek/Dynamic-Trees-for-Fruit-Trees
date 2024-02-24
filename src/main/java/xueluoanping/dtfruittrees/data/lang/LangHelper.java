package xueluoanping.dtfruittrees.data.lang;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.stream.JsonWriter;
import net.minecraft.data.*;

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
	private final ExistingFileHelper helper;
	private final PackOutput output;


	public LangHelper(PackOutput output, ExistingFileHelper helper, String modid, String locale) {
		super(output, modid, locale);
		this.output = output;
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

}
