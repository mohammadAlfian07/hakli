package com.sds.utils.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	private static final ConfigBean config;

	static {
		try {
			Properties prop = new Properties();
			String propFilename = "config.properties";

			InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream(propFilename);
			if (input != null) {
				prop.load(input);
				config = new ConfigBean();
				config.setUrl_base(prop.getProperty("url_base"));
				config.setEndpoint_authadm(prop.getProperty("endpoint_authadm"));
				config.setEndpoint_auth(prop.getProperty("endpoint_auth"));
				config.setEndpoint_user(prop.getProperty("endpoint_user"));
				config.setEndpoint_region(prop.getProperty("endpoint_region"));
				config.setEndpoint_branch(prop.getProperty("endpoint_branch"));
				config.setEndpoint_nation(prop.getProperty("endpoint_nation"));
				config.setEndpoint_provinsi(prop.getProperty("endpoint_provinsi"));
				config.setEndpoint_kabupaten(prop.getProperty("endpoint_kabupaten"));
				config.setEndpoint_rumpun(prop.getProperty("endpoint_rumpun"));
				config.setEndpoint_kepegawaian(prop.getProperty("endpoint_kepegawaian"));
				config.setEndpoint_kepegawaiansdmk(prop.getProperty("endpoint_kepegawaiansdmk"));
				config.setEndpoint_kepegawaiansdmk_grupkepegawaian(prop.getProperty("endpoint_kepegawaiansdmk_grupkepegawaian"));
				config.setEndpoint_university(prop.getProperty("endpoint_university"));
				config.setEndpoint_jenjang(prop.getProperty("endpoint_jenjang"));
				config.setEndpoint_prodi(prop.getProperty("endpoint_prodi"));
				config.setEndpoint_profesi(prop.getProperty("endpoint_profesi"));
				config.setEndpoint_rumpundiklat(prop.getProperty("endpoint_rumpundiklat"));
				config.setEndpoint_jenisdiklat(prop.getProperty("endpoint_jenisdiklat"));
				config.setEndpoint_jenisdiklat_byrumpundiklat(prop.getProperty("endpoint_jenisdiklat_byrumpundiklat"));
				config.setEndpoint_akreditasi(prop.getProperty("endpoint_akreditasi"));
				config.setEndpoint_anggota(prop.getProperty("endpoint_anggota"));
				config.setEndpoint_anggota_pribadi(prop.getProperty("endpoint_anggota_pribadi"));
				config.setEndpoint_anggota_keanggotaan(prop.getProperty("endpoint_anggota_keanggotaan"));
				config.setEndpoint_anggota_kontak(prop.getProperty("endpoint_anggota_kontak"));
				config.setEndpoint_anggota_search(prop.getProperty("endpoint_anggota_search"));
				config.setEndpoint_anggota_count(prop.getProperty("endpoint_anggota_count"));
				config.setEndpoint_anggota_branchtop(prop.getProperty("endpoint_anggota_branchtop"));
				config.setEndpoint_docupload(prop.getProperty("endpoint_docupload"));
				config.setEndpoint_pekerjaan(prop.getProperty("endpoint_pekerjaan"));
				config.setEndpoint_pekerjaan_anggota(prop.getProperty("endpoint_pekerjaan_anggota"));
				config.setEndpoint_pendidikan(prop.getProperty("endpoint_pendidikan"));
				config.setEndpoint_pendidikan_anggota(prop.getProperty("endpoint_pendidikan_anggota"));
				config.setEndpoint_pelatihan(prop.getProperty("endpoint_pelatihan"));
				config.setEndpoint_pelatihan_anggota(prop.getProperty("endpoint_pelatihan_anggota"));
				config.setEndpoint_sertifikasi(prop.getProperty("endpoint_sertifikasi"));
				config.setEndpoint_sertifikasi_anggota(prop.getProperty("endpoint_sertifikasi_anggota"));
				
				System.out.println("--- Initialize Configuration...");
				System.out.println("--- url_base : " + config.getUrl_base());
			} else {
				throw new FileNotFoundException("property file '" + propFilename + "' not found in the classpath");
			}
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static ConfigBean getConfig() {
		return config;
	}

}