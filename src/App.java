import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
 //___________ fazer uma conexão HTTP e buscar os top 250 filmes ________________________________
        
    	String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=872995efee79646f5b0d834c33522673";
    	String prefixo = "https://image.tmdb.org/t/p/w500";
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

//____________ extrair só os dados que interessam (titulo, poster, classificação) ________________        
       
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

//____________ exibir e manipular os dados ________________________________________________________
        
        var resetStyle = "\u001b[m";
        var piscando = "\u001b[5m";
        var negrito = "\u001b[1m";
        var corTxt = "\u001b[31m";
        var corBkg = "\u001b[47m";
        var star = "⭐";
        var shit = "💩";
        var good = "🥔";
        var geradora = new GeradoraDeFigurinhas();
        
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = prefixo + filme.get("backdrop_path");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(corTxt + corBkg + negrito + titulo + resetStyle);
            System.out.println(urlImagem + resetStyle);

            var n = Double.valueOf(filme.get("vote_average")).doubleValue();
            var nota = (double) (Math.round(n*10.0)/10.0);
            
            if(nota >= 8.0){
                for(int count = 0; count < 5; count++){
                    System.out.print(star);
                }
                System.out.println(corBkg + negrito + piscando + nota + resetStyle);

            }else if(nota < 8.0 && nota >= 7.0){
                for(int count = 0; count < 5; count++){
                    System.out.print(good);
                }
                System.out.println(corBkg + negrito + piscando + nota + resetStyle);

            }else if(nota < 7.0){
                for(int count = 0; count < 5; count++){
                    System.out.print(shit);
                }
                System.out.println(corBkg + negrito + piscando + nota + resetStyle);
            }
            System.out.println();
        }
    }
}
