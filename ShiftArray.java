import java.util.ArrayList;

/*
The goal of this project is to create a fluid system for manipulating two-dimensional arrays.
(Should be) usable with any class or variable type.
Includes changable row/column lengths, should be able to update on the fly
Each row/col must be in uniform length with the others
*/

public class ShiftArray {

private ArrayList<ArrayList> arr;

public ShiftArray(int rows, int cols){

    this.arr = new ArrayList();

    if (rows < 1)
        rows = 1;
    if (cols < 1)
        cols = 1;

    for (int j = 0; j < rows; j++){
        ArrayList<Object> hold = new ArrayList();
        for (int i = 0; i < cols; i++){
            hold.add(null);
        }
        arr.add(hold);
    }

}//End constructor, tested

public Object getObj(int row, int col){
    //Returns reference to correct object, defaults to null
    ArrayList<Object> hold = new ArrayList();
    Object o = null;
    if (row < arr.size()){
        hold = arr.get(row);
        if (col < hold.size()){
            o = hold.get(col);
        }//End col if
    }//End row if

    return o;
}//End getObj method, tested

public void addObjRow(Object ob, int row){
    //Adds a given object to the end of given row, creating a new col
    
    if (row > 0){
        if(row >= getNumRows()){
            addRow(row);
        }
        addCol(getNumCols());
        arr.get(row).set(getNumCols()-1, ob);
    }

}//End addObjRow method, tested

public void addObjCol(Object ob, int col){
    //Adds a given object to the end of given col, creating a new row

    if(col > 0){
        if (col >= getNumCols()){
            addCol(col);
        }
        addRow(arr.size());
        arr.get(arr.size()-1).set(col, ob);
    }
        
}//End addObjCol method, tested

public void setObj(Object ob, int row, int col){
    //Sets object at given position
    //If position is not existant, creates it

    if (row < getNumRows()){
        if (col < getNumCols()){
            arr.get(row).set(col, ob);
        }
        else{
            addCol(col);
            arr.get(row).set(col, ob);
        }
    }

    else{
        addRow(row);
        if(col < getNumCols()){
            arr.get(row).set(col, ob);
        }
        else{
            addCol(col);
            arr.get(row).set(col, ob);
        }
    }

}//End setObj method, tested

public void removeObj(int row, int col){
    //Removes a single item from arr, replacing it with null
    //Only works on existant positions

    if (row < getNumRows()){
        if (col < getNumCols()){
            arr.get(row).set(col, null);
        }
    }

}//End removeObj method, tested

public void addRow(int pos){
    /*
    Adds an entire blank row to arr at given position
    Shifts any row elements already in given position downward
    Creates filler rows if given row value is larger than arr.size()
    */

    //Initialize the new row to be added
    ArrayList neu = new ArrayList();
    ArrayList hold = arr.get(0);
    for(int i = 0; i < hold.size(); i++){
        neu.add(null);
    }//End neu creation

    if (pos > arr.size()){
        addRow(pos-1);
    }//End recursive call for if greater position than available

    arr.add(neu);

    if (pos < arr.size()){
        for (int i = arr.size()-1; i > pos; i--){

            arr.set(i, arr.get(i-1));

        }//End position for
        arr.set(pos, neu);
    }//End shift if

}//End addRow method, tested

public void removeRow(int pos){
    //Removes an entire row from arr

    if (pos < arr.size() && arr.size()-1 > 0)
        arr.remove(pos);

}//End removeRow method, tested

public void addCol(int pos){
    /*
    Adds an entire blank col to arr at given position
    Shifts any col elements already in given position to the right
    Creates filler columns if given col value is larger than arr.size()
    */

    if (pos > getNumCols()){
        addCol(pos-1);
    }//End recursive call for if greater position than available

    for(int i = 0; i < getNumRows(); i++){
        arr.get(i).add(null);
    }//End addition of null

    if (pos < getNumRows()){
        for (int i = 0; i < getNumRows(); i++){
            for(int j = getNumCols()-1; j > pos; j--){
                arr.get(i).set(j, arr.get(i).get(j-1));
            }
            arr.get(i).set(pos, null);
        }//End position for
    }//End shift if

}//End addCol method, tested

public void removeCol(int pos){
    //Removes an entire column from arr

    ArrayList hold = arr.get(0);
    if(pos < hold.size() && hold.size()-1 > 0){
        for (int i = 0; i < arr.size(); i++){
            arr.get(i).remove(pos);
        }//End for
    }//End if

}//End removeCol method, tested

public int getNumCols(){
    //Returns the ShiftArray equivalent of c, or number of columns

    return arr.get(0).size();

}//End getNumCols method, tested

public int getNumRows(){
    //Returns the ShiftArray equivalent of r, or number of rows

    return arr.size();

}//End getNumRows method, tested

public ArrayList<Object> getRow(int pos){
    //Returns an ArrayList of the row at given position, null if none

    if (pos < arr.size())
        return arr.get(pos);
    else
        return null;

}//End getRow method, tested

public ArrayList<Object> getCol(int pos){
    //Returns an ArrayList of the col at given position, null if none

    ArrayList<Object> hold = new ArrayList();
    if(pos < arr.get(0).size()){
        for (int i = 0; i < arr.size(); i++){
            hold.add(arr.get(i).get(pos));
        }//End for
        return hold;
    }//End if

    else
        return null;

}//End getCol method, tested

}//End class
