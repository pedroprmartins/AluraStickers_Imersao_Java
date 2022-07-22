import java.net.URI;
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
        
        String resetStyle = "\u001b[m";
        String piscando = "\u001b[5m";
        String negrito = "\u001b[1m";
        String corTxt = "\u001b[31m";
        String corBkg = "\u001b[47m";
        var star = "⭐";
        var shit = "💩";
        var good = "🥔";
        
        for (Map<String,String> filme : listaDeFilmes) {

            System.out.println(corTxt+corBkg+negrito+ filme.get("title")+resetStyle);
            System.out.println(prefixo+ filme.get("backdrop_path")+resetStyle);

            var n = Double.valueOf(filme.get("vote_average")).doubleValue();
            var nota = (double) (Math.round(n*10.0)/10.0);
            
            if(nota >= 8.0){
                for(int count = 0; count < 5; count++){
                    System.out.print(star);
                }
                System.out.println(corBkg+negrito+piscando+ nota +resetStyle);

            }else if(nota < 8.0 && nota >= 7.0){
                for(int count = 0; count < 5; count++){
                    System.out.print(good);
                }
                System.out.println(corBkg+negrito+piscando+ nota +resetStyle);

            }else if(nota < 7.0){
                for(int count = 0; count < 5; count++){
                    System.out.print(shit);
                }
                System.out.println(corBkg+negrito+piscando+ nota +resetStyle);
            }
            System.out.println();
        }
    }
}
