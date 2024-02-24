package xueluoanping.dtfruitfulfun.data.lang;


import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;


public class Lang_ZH extends LangHelper {
	public Lang_ZH(PackOutput gen, ExistingFileHelper helper) {
		super(gen, helper, DTFruitfulFun.MOD_ID, "zh_cn");
	}


	@Override
	protected void addTranslations() {
		add(DTFruitfulFun.MOD_ID, "动态的树：果树附属");
		add(RegisterFinderUtil.getBlock("dtfruittrees:cherry_sapling"), "樱桃树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:mandarin_sapling"), "橘子树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:lime_sapling"), "酸橙树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:citron_sapling"), "香橼树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:pomelo_sapling"), "柚子树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:orange_sapling"), "甜橙树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:lemon_sapling"), "柠檬树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:grapefruit_sapling"), "西柚树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:redlove_sapling"), "红心果树苗");
		add(RegisterFinderUtil.getBlock("dtfruittrees:cherry_branch"), "樱桃木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:mandarin_branch"), "橘子木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:lime_branch"), "酸橙木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:citron_branch"), "香橼木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:pomelo_branch"), "柚子木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:orange_branch"), "甜橙木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:lemon_branch"), "柠檬木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:grapefruit_branch"), "西柚木");
		add(RegisterFinderUtil.getBlock("dtfruittrees:redlove_branch"), "红心果木");
		add(RegisterFinderUtil.getItem("dtfruittrees:cherry_seed"), "樱桃种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:mandarin_seed"), "橘子种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:lime_seed"), "酸橙种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:citron_seed"), "香橼种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:pomelo_seed"), "柚子种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:orange_seed"), "甜橙种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:lemon_seed"), "柠檬种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:grapefruit_seed"), "西柚种子");
		add(RegisterFinderUtil.getItem("dtfruittrees:redlove_seed"), "红心果种子");
		addSpecie("cherry","樱桃树");
		addSpecie("mandarin","橘子树");
		addSpecie("lime","酸橙树");
		addSpecie("citron","香橼树");
		addSpecie("pomelo","柚子树");
		addSpecie("orange","甜橙树");
		addSpecie("lemon","柠檬树");
		addSpecie("grapefruit","西柚树");
		addSpecie("redlove","红心果树");
	}


}
