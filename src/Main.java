import Connection.CEPAPI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CEPAPI api= new CEPAPI();
        Scanner sc= new Scanner(System.in);
        String i="";
        while(!i.equalsIgnoreCase("sair")){
            if(i.equalsIgnoreCase("sair")) break;

                System.out.println("digite o CEP que quer pesquisar, apenas numeros: ");
                i = sc.nextLine();
                System.out.println(api.pesquisarCEP(i));
            }
            api.CriarDocumento();


    }
}