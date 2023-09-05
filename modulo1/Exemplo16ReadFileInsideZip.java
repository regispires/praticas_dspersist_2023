import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

public class Exemplo16ReadFileInsideZip {

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream(args[0]);
		ZipInputStream zis = new ZipInputStream(is);
		zis.getNextEntry();
		Scanner sc = new Scanner(zis);
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		sc.close();
	}
}
