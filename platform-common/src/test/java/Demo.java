
public class Demo {
    private String id;
    private String name;
    private String input;
    private String output;

    public Demo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Demo(String id, String name, String input) {
        this(id, name);
        this.input = input;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getInput(){
        return this.input;
    }

    public String getOutput(){
        return this.output;
    }

    public void setInput(String input){
        this.input = input;
    }

    public void execute(){
        this.output = this.input + "(Down)";
    }
}
