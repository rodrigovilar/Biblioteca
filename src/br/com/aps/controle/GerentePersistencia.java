package br.com.aps.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import br.com.aps.entidade.Persistencia;


public class GerentePersistencia implements Serializable {

	private static Persistencia instance = null;

	public GerentePersistencia() {
	}

	// singleton

	public static Persistencia getInstance() {
		if (instance == null) {
			// "lazy instantiation"
			instance = new Persistencia();
		}
		return instance;
	}

	public static void persistir() {

		FileOutputStream f = null;
		ObjectOutputStream stream = null;

		try {
			File fi = new File("dados.bin");

			f = new FileOutputStream(fi);
			stream = new ObjectOutputStream(f);
			stream.writeObject(instance);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void recuperar() {

		File temp = new File("dados.bin");
		if (temp.exists()) {
			FileInputStream fis = null;
			ObjectInputStream stream = null;
			try {
				fis = new FileInputStream("dados.bin");
				stream = new ObjectInputStream(fis);
				instance = (Persistencia) stream.readObject();
			} catch (Exception e) {

				e.printStackTrace();
			} finally {

				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}

				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void apagarConteudoArquivo() throws IOException {
		File file = new File("dados.bin");
		if (file.exists()) {
			file.delete();
		}
	}

	public static void reset() {
		instance = null;
	}
}
