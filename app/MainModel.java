public class MainModel {

    String nombre, apellidoPaterno, ApellidoMaterno, correo, sexo, telefono;
    int edad;

    public MainModel(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String sexo, String telefono, int edad) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        ApellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.sexo = sexo;
        this.telefono = telefono;
        this.edad = edad;
    }

    public MainModel() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
