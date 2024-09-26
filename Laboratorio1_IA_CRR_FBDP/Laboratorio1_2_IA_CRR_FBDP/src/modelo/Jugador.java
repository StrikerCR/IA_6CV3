package modelo;

/**
 *
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 *
**/

public class Jugador {
    
    private String nombre;
    private TipoImagen tipoImagen;
    private int tablero[][];
    
    public Jugador(){
        tablero = new int[4][4];
        limpiar();
    }
    public Jugador(TipoImagen tipoImagen){
        this.tipoImagen = tipoImagen;
        tablero = new int[4][4];
        limpiar();
    }
    public Jugador(String nombre, TipoImagen tipoImagen){
        this.nombre = nombre;
        this.tipoImagen = tipoImagen;
        tablero = new int[4][4];
        limpiar();
    }
    
    public void limpiar(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tablero[i][j] = 0;
            }
        }
    }
    
    public TipoImagen cuatroEnRaya(Jugador jugadorRival){
        
        if(tablero[0][0]==1 && tablero[0][1]==1 && tablero[0][2]==1 && tablero[0][3]==1) return tipoImagen.LINEA1;
        if(tablero[1][0]==1 && tablero[1][1]==1 && tablero[1][2]==1 && tablero[1][3]==1) return tipoImagen.LINEA2;
        if(tablero[2][0]==1 && tablero[2][1]==1 && tablero[2][2]==1 && tablero[2][3]==1) return tipoImagen.LINEA3;
        if(tablero[3][0]==1 && tablero[3][1]==1 && tablero[3][2]==1 && tablero[3][3]==1) return tipoImagen.LINEA4;
        
        if(tablero[0][0]==1 && tablero[1][0]==1 && tablero[2][0]==1 && tablero[3][0]==1) return tipoImagen.LINEA5;
        if(tablero[0][1]==1 && tablero[1][1]==1 && tablero[2][1]==1 && tablero[3][1]==1) return tipoImagen.LINEA6;
        if(tablero[0][2]==1 && tablero[1][2]==1 && tablero[2][2]==1 && tablero[3][2]==1) return tipoImagen.LINEA7;
        if(tablero[0][3]==1 && tablero[1][3]==1 && tablero[2][3]==1 && tablero[3][3]==1) return tipoImagen.LINEA8;

        if(tablero[0][0]==1 && tablero[1][1]==1 && tablero[2][2]==1 && tablero[3][3]==1) return tipoImagen.LINEA10;
        if(tablero[0][3]==1 && tablero[1][2]==1 && tablero[2][1]==1 && tablero[3][0]==1) return tipoImagen.LINEA9;
        
        int cont = 0;
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(tablero[i][j] == 1){
                    cont++;
                }
                if(jugadorRival.getTablero()[i][j] == 1){
                    cont++;
                }
            }
        }
        
        if(cont == 16) return TipoImagen.EMPATE;
        
        return null;
        
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoImagen getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
    }
    
    
    
}
