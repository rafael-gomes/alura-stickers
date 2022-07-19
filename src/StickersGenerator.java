import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGenerator {
  
  void cria(InputStream inputStream, String nomeArquivo, String titulo) throws IOException {
    //InputStream inputStream = new FileInputStream(new File("assets/filme.jpg"));
    //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);
    
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 32);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);
    graphics.drawString(titulo, 100, novaAltura - 100);

    ImageIO.write(novaImagem, "png", new File("assets/stickers/" + nomeArquivo));
  }

}
