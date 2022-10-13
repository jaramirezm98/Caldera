/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Aitor Etxabarren
 * @version 1.0
 */
public class Caldera {
    
    // TODO: CONSTANTES
    private final double IMP_IVA = 0.22;
    private final double IMP_HIDROCARBUROS = 0.20;
    
    private final char AGUA = 'A';
    private final char LUZ = 'L';
    private final char NADA = 'N';
    
    private final int NINGUNO = 0;
    private final int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private final int PERIODO_ENERO_MARZO = 2;
    private final int PERIODO_ABRIL_JUNIO = 3;
    private final int PERIODO_JULIO_SEPTIEMBRE = 4;
    
    private int vecinos;
    private double presupuesto;
    
    private double acumuladoConsumo;
    private double acumuladoMantenimiento;
    private double gastoAgua;
    private double gastoLuz;
    
    private int mesMasConsumo;
    private double maxConsumo;
    private int mesMasCaro;
    private double maxPrecio;
    private int mesMasBarato;
    private double minPrecio;
    
    private int periodoMasMantenimiento;
    private double maxMantenimiento;
    
    private int mesMasGasto;
    private double maxGasto;
    private char conceptoMasGasto;
    
    
    
    /** El IVA se aplica a todos los gasto. */
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    // Conceptos gastos
    // Ningun mes
    // Periodos
    // TODO: VARIABLES DE CLASE (PROPIEDADES/ATRIBUTOS)
    // vecinos y presupuesto
    // acumulados
    // estadisticas
    // TODO: constructores
    
    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     */
    public Caldera(){
        vecinos = 0;
        presupuesto = 0;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = NINGUNO;
        maxConsumo = 0;
        mesMasCaro = NINGUNO;
        maxPrecio = 0;
        mesMasBarato = NINGUNO;
        minPrecio = 0;
        periodoMasMantenimiento = NINGUNO;
        maxMantenimiento = 0;
        mesMasGasto = NINGUNO;
        maxGasto = 0;
        conceptoMasGasto = NADA;
    }

    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     * 
     * @param queVecinos     Numero de vecinos que conforman la comunidad
     * @param quePresupuesto Presupuesto inicial con el que se pretende afrontar los
     *                       gastos
     */
    public Caldera(int queVecinos, int quePresupuesto){
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = NINGUNO;
        maxConsumo = 0;
        mesMasCaro = NINGUNO;
        maxPrecio = 0;
        mesMasBarato = NINGUNO;
        minPrecio = 0;
        periodoMasMantenimiento = NINGUNO;
        maxMantenimiento = 0;
        mesMasGasto = NINGUNO;
        maxGasto = 0;
        conceptoMasGasto = NADA;
    }

    /**
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */
    public void setPresupuesto (double quePresupuesto){
        presupuesto = quePresupuesto;
    }

    /**
     * Obtiene el valor del presupuesto
     * 
     * @return valor del presupuesto, ej. 38638
     */
    public double getPresupuesto(){
        return presupuesto;
    }

    /**
     * Fija el numero de vecinos de la comunidad
     * 
     * @param queVecinos numero de vecinos, ej. 48
     */
    public void setVecinos(int queVecinos){
        vecinos = queVecinos;
    }

    /**
     * Obtiene el numero de vecinos
     * 
     * @return numero de vecinos, ej. 48
     */
    public int getVecinos(){
        return vecinos;
    }

    /**
     * Cantidad de gas consumido cada mes al precio de mercado
     * 
     * @param mes    Numero de mes, 1 es enero, 2 febrero, ..., 12 diciembre, ej. 9
     * @param gas    Cantidad de gas consumido en KWh, ej. 15496
     * @param precio Precio en Euros al que se ha conseguido el gas, ej. 0.067668
     */
    public void consumo(int mes, int gas, double precio) {
        //REVISAR
        acumuladoConsumo = gas*precio;
        if (maxConsumo < acumuladoConsumo){
            maxConsumo = acumuladoConsumo;
            mesMasConsumo = mes;
        }
        if (maxPrecio < precio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        if (minPrecio < precio){
            minPrecio = precio;
            mesMasBarato = mes;
        }
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * 
     * @param periodo Numero que representa el periodo, ej. OCTUBRE-DICIEMBRE es 1
     * @param importe Valor del gasto de mantenimiento
     */
    public void mantenimiento(int periodo, double importe) {
        // REVISAR
        acumuladoMantenimiento = acumuladoMantenimiento + importe;
        if(maxMantenimiento > importe){
            maxMantenimiento = importe;
            periodoMasMantenimiento = periodo;
        }
    }

    /**
     * Gasto mensual en concepto de agua o luz.
     * 
     * @param mes      Numero del mes, ej. ENERO es 1
     * @param concepto Agua 'A' o luz 'L'
     * @param importe  Valor del gasto, ej. 189.03
     */
    public void gasto(int mes, char concepto, double importe) {
        // TODO: gasto
        if (concepto == 'A'){
            gastoAgua = gastoAgua + importe;
        }
        if (concepto == 'L'){
            gastoLuz = gastoLuz + importe;
        }
        if (importe > maxGasto){
            maxGasto = importe;
            mesMasGasto = mes;
            conceptoMasGasto = concepto;
        }
    }

    /**
     * Imprime el resultado del periodo, ej.
     * 
     * ==================
     * RESULTADO GLOBAL
     * ==================
     * Presupuesto: 38638.0
     * Consumo gas: 61688.26
     * Impuestos g.: 25909.07
     * Mantenimiento: 4157.58
     * Iva manto.: 914.67
     * Gasto agua: 2647.83
     * Iva agua: 582.52
     * Gasto luz: 4663.01
     * Iva luz: 1025.86
     * ------------------
     * TOTAL : -62950.8 Euros.
     * ------------------
     * ==================
     * RESULTADO X VECINO
     * ==================
     * Vecinos: 48
     * Aporte v.: 804.96
     * Gasto v.: 2116.43
     * Resultado: -1311.47
     * ------------------
     * El resultado ha sido NEGATIVO,
     * se tiene que pagar 1311.47 Euros.
     * El pago se pasara en
     * 5 cuotas de 262.29 Euros.
     * ------------------
     */
    public void printResultados() {
        // TODO: printResultados
        double impuestosGas = acumuladoConsumo * IMP_IVA + acumuladoConsumo * IMP_HIDROCARBUROS;
        double impuestosMantenimiento = acumuladoMantenimiento * IMP_IVA;
        double impuestosAgua = gastoAgua * IMP_IVA;
        double impuestosLuz = gastoLuz * IMP_IVA;
        double totalGasto = presupuesto - acumuladoConsumo - impuestosGas - acumuladoMantenimiento 
        - impuestosMantenimiento - gastoAgua - impuestosAgua - gastoLuz - impuestosLuz;
        double aporteVecino = presupuesto / vecinos;
        double gastoVecino = (presupuesto + acumuladoConsumo + impuestosGas + acumuladoMantenimiento 
        + impuestosMantenimiento + gastoAgua + impuestosAgua + gastoLuz + impuestosLuz) / vecinos;
        double resultadoAporte = totalGasto / vecinos;
        
        System.out.println("=================="+ "\n" + "Presupuesto: " + presupuesto+ 
        "\n" + "Consumo gas: " + acumuladoConsumo +
        "\n" + "Impuestos g.: " + impuestosGas +
        "\n" + "Mantenimiento: " + acumuladoMantenimiento +
        "\n" + "Iva manto.: " + impuestosMantenimiento +
        "\n" + "Gasto Agua: " + gastoAgua +
        "\n" + "Iva agua: " + impuestosAgua +
        "\n" + "Gasto luz: " + gastoLuz +
        "\n" + "Iva luz: " + impuestosLuz +
        "\n" + "-------------------" + "\n" + "TOTAL : " + totalGasto +
        "\n" + "-------------------" + "\n" + "==================" + "\n" + "RESULTADO X VECINO" + "\n" + 
        "==================" + "\n" + "Vecinos: " + vecinos +
        "\n" + "Aporte v.: " + aporteVecino +
        "\n" + "Gasto v.: " + gastoVecino +
        "\n" + "Resultado: " + resultadoAporte+
        "\n" + "-------------------" );
        
        if (resultadoAporte < 0){
            System.out.println("El resultado ha sido NEGATIVO," + "\n" + "se tienen que pagar " + 
            resultadoAporte+ "\n" + "El pago se pasará en 5 cuotas de ");     //TODO
        }
        if (resultadoAporte > 0){
            System.out.println("El resultado ha sido POSITIVO,"+ "\n" + "se tienen que devolver "+
            resultadoAporte);
        }
    }

    /**
     * Imprime las estadisticas del periodo, ej.
     * 
     * ==================
     * ESTADISTICAS
     * ==================
     * Max. consumo: ENERO 12527.66
     * Mes mas caro: AGOSTO 0.202504
     * Mes mas barato: SEPTIEMBRE 0.067668
     * Mayor gasto en: ABRIL 679.94 LUZ
     * P. mas manto.: OCTUBRE-DICIEMBRE 1552.1
     * ------------------
     */
    public void printEstadisticas() {
        System.out.println( "==================" + "\n" + "   ESTADISTICAS   " + 
        "\n" + "==================" + "\n" + "Max. consumo: " + getNombreMes(mesMasConsumo) +" "+ mesMasConsumo +
        "\n" + "Mes mas caro: " + getNombreMes(mesMasCaro) +" "+ mesMasCaro +
        "\n" + "Mes mas barato: " + getNombreMes(mesMasBarato) +" "+ mesMasBarato+
        "\n" + "Mayor gasto en: " + getNombreMes(mesMasGasto)+ " "+ mesMasGasto+" "+ getNombreConcepto(conceptoMasGasto) +
        "\n" + "P. mas manto.: " + getNombrePeriodo(periodoMasMantenimiento)+ " "+ periodoMasMantenimiento );
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        switch(numMes){
            case 1: 
                return "ENERO";
            case 2:
                return "FEBRERO";   
            case 3:
                return "MARZO";
            case 4:
                return "ABRIL";
            case 5:
                return "MAYO";
            case 6:
                return "JUNIO";
            case 7:
                return "JULIO";
            case 8:
                return "AGOSTO";
            case 9:
                return "SEPTIEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVIEMBRE";
            case 12:  
                return "DICIEMBRE";
            default:
                return "NINGUNO";
        }        
    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        if (concepto == 'L'){
            return "LUZ";
        }else if (concepto == 'A'){
            return "AGUA";
        }else{
            return "NADA";
        }
    }

    /**
     * Devuelve el nombre del periodo asociado a su numero
     * 
     * @param numPeriodo Numero de periodo, del 1 al 4, ej. 4
     * @return Nombre del periodo con nombres de los meses separados por guion, ej.
     *         "OCTUBRE-DICIEMBRE". Sino devuelve "NINGUN PERIODO"
     */
    public String getNombrePeriodo(int numPeriodo) {
        switch (numPeriodo){
            case 1:
                return "ENERO-MARZO";
            case 2:
                return "ABRIL-JUNIO";
            case 3:
                return "JULIO-SEPTIEMBRE";
            case 4:
                return "OCTUBRE-DICIEMBRE";
            default:
                return "NINGUN PERIODO";
        }        
    }

    /**
     * Analiza el resultado, si el valor es negativo se tendra que pagar si es
     * positivo se devolvera.
     * En el caso negativo se debera pagar de una vez si el importe en menor o igual
     * que 200,
     * en multiplos de 200 y el resto si el resultado es menor o igual que 600 o
     * en 5 partes alicuotas sino.
     * 
     * @param resultado cantidad positiva o negativa, ej. -1311.47
     * @return Mensaje con la informacion sobre el pago. Ej.
     *         </br>
     *         El resultado ha sido NEGATIVO,
     *         se tiene que pagar 114.56 Euros.
     *         El pago se pasara en un solo cobro.
     *         </br>
     *         El resultado ha sido NEGATIVO,
     *         se tiene que pagar 513.33 Euros.
     *         El pago se pasara en
     *         2 cuotas de 200 Euros y
     *         otro cobro de 113.33 Euros.
     *         </br>
     *         El resultado ha sido NEGATIVO,
     *         se tiene que pagar 1311.47 Euros.
     *         El pago se pasara en
     *         5 cuotas de 262.29 Euros.
     *         </br>
     *         El resultado ha sido POSITIVO,
     *         se devolvera 45.52 Euros.
     *         El pago se realizara en breve en
     *         una transferencia.
     */
    public String analisisResultado(double resultado) {
        // TODO: analisisResultado
        double resto = 0;
        int cuotas = 0;
        String mensaje = "NINGUNO";
        if (resultado >= -200 && resultado < 0){
            resultado = -(resultado);
            mensaje = ("El resultado ha sido NEGATIVO,"+"\n"+
            "se tiene que pagar "+ resultado +" Euros."+ "\n" +
            "El pago se pasara en un solo cobro");
        }else if (resultado<-200 && resultado>=-600){
            resto = resultado%200;
            cuotas = (int) resultado/200;
            resultado = -(resultado);
            mensaje = ("El resultado ha sido NEGATIVO,"+"\n"+
            "se tiene que pagar "+ resultado +" Euros."+ "\n" +
            "El pago se pasara en "+ cuotas+ " cuotas de 200 Euros y otro cobro de" + resto);
        }else if (resultado < -600){
            double dineroCuotas = resultado / 5;
            mensaje = ("El resultado ha sido NEGATIVO,"+ "\n" +
            "se tiene que pagar "+ resultado+" Euros." + "\n" +
            "El pago se pasara en" + "\n" +
            "5 cuotas de" + dineroCuotas + " Euros.");
        }else if (resultado >= 0){
             mensaje = ("El resultado ha sido POSITIVO,"+ "\n" +
             "se devolvera "+ resultado +"Euros."+ "\n" +
             "El pago se realizara en breve en" + "\n" +
             "una transferencia.");
        }
        return mensaje;
    }

    /**
     * Redondea un valor de tipo double a dos decimales.
     * Al imprimirlo se vera al menos un decimal y como mucho dos.
     *
     * @param valor Numero con decimales de tipo double
     * @return Redondeo con 1 o 2 decimales, ej.
     *         38638 -> 38638.0
     *         61688.255730000004 -> 61688.26
     *         25909.067406600003 -> 25909.07
     *         -62950.79553660001 -> -62950.8
     *         -1311.4749070125 -> -1311.47
     */
    public double redondeo2decimales(double valor) {
        valor = (double)Math.round(valor * 100d)/100;
        return valor;
    }

    /**
     * Divide un numero decimal entre un numero entero y devuelve el resultado
     * entero.
     * 
     * @param dividendo Numero con decimales que se divide, ej. 647.55
     * @param divisor   Numero entero que divide, ej. 200
     * @return Cociente, numero entero, cuantos divisores caben en el dividendo, ej.
     *         3
     */
    public int divisionEntera(double dividendo, int divisor) {
        return (int)dividendo/divisor;
    }

    /**
     * Divide un numero decimal entre un numero entero y devuelve el resto con
     * decimales.
     * 
     * @param dividendo Numero con decimales que se divide, ej. 647.55
     * @param divisor   Numero entero que divide, ej. 200
     * @return Resto con decimales, ej. 47.55
     */
    public double restoDivisionEntera(double dividendo, int divisor) {
        return dividendo/(double)divisor;
    }

}
