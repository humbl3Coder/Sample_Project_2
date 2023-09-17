
package interfaces;

import javafx.collections.ObservableList;
import model.Divisions;

/**
 * This interface provides functionality for Lambda 1.
 */

public interface Filtered{

    ObservableList<Divisions> filter(int id);
}
