package validadorcmc7.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author re
 */
public class verificar {

//Campo = Codigo completo do CMC7
    public static boolean validarCMC7(String campo) {

            String regexValida = "\\d{8}\\d{10}\\d{12}";
            String regexInvalida = "[0]{8}[0]{10}[0]{12}";

            Pattern invalido = Pattern.compile(regexInvalida);
            Pattern valido = Pattern.compile(regexValida);

            Matcher mInvalido = invalido.matcher(campo);
            Matcher mValido = valido.matcher(campo);

            if ( (mInvalido.matches()) || (! mValido.matches()) ) {
                return false;
            }

            String grupo1 = campo.substring(0,7);
            String grupo2 = campo.substring(8,18);
            String grupo3 = campo.substring(19,29);

            boolean ret1 = (modulo10(grupo1).equals(campo.substring(18,19)));
            boolean ret2 = (modulo10(grupo2).equals(campo.substring(7,8)));
            boolean ret3 = (modulo10(grupo3).equals(campo.substring(29,30)));

            if( ret1 && ret2 && ret3) {
                return true;
            } else {
                return false;
            }

        }

        /**
        * Função modulo10. Calcula o módulo10 de uma string numerica e retorna
        * o digito
        *
        *   @param numero
        *       String contendo o numero a ter o mod10 calculado
        *
        *   @return String
        *           String contendo o DAC do numero calculado
        *
        */
        private static String modulo10(String numero) {

            int multi, posicao1, posicao2, acumula, resultado, dac;

            dac = 0;
            posicao1 = numero.length()-1;
            multi   = 2;
            acumula = 0;

            while (posicao1 >= 0) {

                resultado = Integer.parseInt(numero.substring(posicao1, posicao1 + 1)) * multi;
                posicao2  = Integer.toString(resultado).length()-1;

                while (posicao2 >= 0) {
                    acumula += Integer.parseInt(Integer.toString(resultado).substring(posicao2,posicao2 + 1));
                    posicao2--;
                }

                multi = (multi == 2) ? 1 : 2;
                posicao1--;
            }

            dac = acumula % 10;
            dac = 10 - dac;

            if (dac == 10) {
                dac = 0;
            }

            return String.valueOf(dac);
         }


}
