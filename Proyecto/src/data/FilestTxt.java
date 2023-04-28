package data;

//librerias para escritura y lectura de archivos
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class FilestTxt {
//crea el archivo y la ubicacion a escribir
	private FileWriter file;
//objeto que escribe en el archivo creado
	private PrintWriter pw;

//es la ubicacion con el archivo a java
	private File fileAddress;
//objeto que permite leer el archivo seleccionado
	private FileReader fr;
//objeto que almacena el archivo leido
	private BufferedReader br;

	public void writeFile(String address, String data) {

		try {
			// se crea el archivo y se dan las propiedades de escritura (boolean)

			file = new FileWriter(address, true);
			// se crea el objeto que tiene la propiedad de escribir en el archivo

			pw = new PrintWriter(file);

			pw.println(data);
		} catch (IOException ioe) {

			JOptionPane.showMessageDialog(null, ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				file.close();// cerrrar el archivo de texto
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public String readFile(String address) {

		String information = "";
		String line = "";

		try {
			fileAddress = new File(address);
			fr = new FileReader(fileAddress);
			br = new BufferedReader(fr);

			// leo una linea y si tiene un valor lo almacena
			while ((line = br.readLine()) != null) {
				information += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return information;
	}
}
