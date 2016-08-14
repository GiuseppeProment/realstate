package realstate.api.util;

import realstate.api.domain.Province;

public interface Fixture {
	Province[] ARRAY_PROVINCES_WITH_SCAVY = new Province[]{new Province("Scavy")};
	Province[] ARRAY_PROVINCES_WITH_GODE = new Province[]{new Province("Gode")};
	String PROVINCE_SCAVY_NAME = "Scavy";
	String PROVINCE_RUJA_NAME = "Ruja";
	String PROVINCE_GODE_NAME = "Gode";
}
