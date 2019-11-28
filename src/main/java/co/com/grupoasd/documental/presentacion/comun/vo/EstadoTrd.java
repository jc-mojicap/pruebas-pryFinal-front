package co.com.grupoasd.documental.presentacion.comun.vo;

public enum EstadoTrd {
    ACTIVA(1, "A"), INACTIVA(2, "I"), EN_EDICION(3, "E"),;
    
    /**
     * estado_id.
     */
    private Integer id;
    /**
     * nombre.
     */
    private String nombre;
    
    /**
     * Constructor.
     * @param id
     * @param nombre
     */
    private EstadoTrd(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /**
     * getId.
     * @return char
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * getNombre.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }    
    
    /**
     * getEstadoTrd
     * @param id
     * @return
     */
    public static EstadoTrd getEstadoTrd(int id) {
        for (EstadoTrd estado : EstadoTrd.values()) {
            if (estado.getId() == id)
                return estado;
        }
        return null;
    }
    
    
	
}
