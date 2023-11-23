package java_project;

import java.util.ArrayList;

public class StudentManagement {

// JUST TEST

//----------DATA FIELDS------------------------------------------------------------------------------------

private ArrayList<Student> student1 = new ArrayList<>();






//-------------METHODS--------------------------------------------------------------------------------------

//------------STUDENT MANAGER  NOT IMPORTANT----------------------------------------------
public void StudentManagement()
{

}


//--------------- ADD STUDENT----------------------------------------------
public void addStd(Student student)
{
student1.add(student);
}


//----------------- DELETE STUDENT------------------------------------------

public  boolean deleteStd(int id)
{
int index=findStdIndex(id);
if(index!=0){
    student1.remove(student1.get(index));
    return true;
}
return false;
}


//-------------------UPDATE STUDENT USERNAME---------------------------------
public  boolean updateStdUsername(int id,String newUser)
{
int index=findStdIndex(id);
if(index!=0){
    student1.get(index).setUsername(newUser);
    return true;
}
return false;

}



//------------------UPDATE STUDENT PASSWORD-----------------------------------
public boolean updateStdPassword(int id,String newPass)
{
int index=findStdIndex(id);
if(index!=0){
    student1.get(index).setPassword(newPass);
    return true;
}
return false;
}



//-------------------SEARCH STUDENT--------------------------------------------
public ArrayList<Student> searchStd(int id)  //msh 3arfa eh da

{
   
    int index=findStdIndex(id);
    if(index!=0){
        student1=Student.get(index);
   
}
    return student1;
    
    
}



//----------------------LIST STUDENT---------------------------------------------
public ArrayList<Student> listStd()
{

    return student1;

}






private int findStdIndex(int id) {

        if (id <= 0) {
            return 0;
        }

        for (int i = 0; i < student1.size(); i++) {
            if (id == ( student1.get(i)).getID()) {

                return i;  // return the index of the student
            }
        }

        return 0;
    }






}
