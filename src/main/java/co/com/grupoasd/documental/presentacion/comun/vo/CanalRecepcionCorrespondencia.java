/*
* Archivo: CanalCorrespondencia.java
* Fecha creacion = 16/03/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
* SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito del GRUPO ASD S.A.S.
* autorizacion por parte de su autor quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.documental.presentacion.comun.vo;

public enum CanalRecepcionCorrespondencia {
    FISICO(1, "Físico"), CORREO_CERTIFICADO(2, "Correo certificado"), CORREO_ELECTRONICO(3, "Correo electrónico"), OTROS(4, "Otros");
    
    private int id;
    private String nombre;

    private CanalRecepcionCorrespondencia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
    	return nombre;
    }

    public static CanalRecepcionCorrespondencia getCanalRecepcionCorrespondencia(int id) {
        for (CanalRecepcionCorrespondencia canal : CanalRecepcionCorrespondencia.values()) {
            if (id == canal.getId())
                return canal;
        }
        return null;
    }

}
