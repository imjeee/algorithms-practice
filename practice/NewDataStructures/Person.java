package NewDataStructures;
import java.util.List;


public class Person {
  private String name;
  private boolean available;
  private List<String> preferences;
  
  public Person(String name, boolean available, List<String> preferences) {
    this.name = name;
    this.setAvailable(available);
    this.preferences = preferences;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public List<String> getPreferences() {
    return preferences;
  }
  public void setPreferences(List<String> preferences) {
    this.preferences = preferences;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
