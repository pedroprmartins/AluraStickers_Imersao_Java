import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStream;
//import java.net.URL;
//import java.text.Format;

import javax.imageio.ImageIO;
//import javax.swing.text.StyledEditorKit.FontSizeAction;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //Leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://image.tmdb.org/t/p/w500/9eAn20y26wtB3aet7w9lHjuSgZ3.jpg").openStream(); 
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Criar nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 100;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //Copiar a imagem original para novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.createGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //Escrever um frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 50);

        //Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}
