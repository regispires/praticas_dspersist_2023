import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Exemplo17CreateZip {

    public static void main(String[] args) throws IOException {
        String zipFileName = args[0]; 
        String sourceFolder = args[1]; 
        zipDirectory(sourceFolder, zipFileName);
        // Compactação concluída com successo
        System.out.println("Compacta\u00E7\u00E3o conclu\u00EDda com successo");
    }

    public static void zipDirectory(String sourceFolder, String zipFileName) throws IOException {
        File sourceFolderFile = new File(sourceFolder);
        File zipFile = new File(zipFileName);

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            zipFolder(sourceFolderFile, sourceFolderFile.getName(), zos);
        }
    }

    private static void zipFolder(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipFolder(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }

            try (FileInputStream fis = new FileInputStream(file)) {
                String entryName = parentFolder + "/" + file.getName();
                ZipEntry zipEntry = new ZipEntry(entryName);
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            }
        }
    }
}
