public class Rating {
    
    private String resetStyle = "\u001b[m";
    private String piscando = "\u001b[5m";
    private String negrito = "\u001b[1m";
    private String corTxt = "\u001b[31m";
    private String corBkg = "\u001b[47m";
    private String star = "⭐";
    private String shit = "💩";
    private String good = "🥔";
    private int count = 0;
    
    public String modTitulo(String titulo){
         return this.corTxt + this.corBkg + this.negrito + titulo + this.resetStyle;
    }

    public String urlImagem(String urlImagem){
        return urlImagem;
    }
    
    public void ratingMovie(String vote) {

        var valor = Double.valueOf(vote).doubleValue();
        var nota = (double) (Math.round(valor*10.0)/10.0);

        if(nota >= 8.0){
            while(count < 5){
                System.out.print(star);
                count++;
            }
        }else if(nota < 8.0 && nota >= 7.0){
            while(count < 5){
                System.out.print(good);
                count++;
            }
        }else if(nota < 7.0){
            while(count < 5){
                System.out.print(shit);
                count++;
            }   
        }
        System.out.println(corBkg + negrito + piscando + nota + resetStyle);
    }
}
