package ListsWithoutGenerics.ZeichenketteListe;

public class MyString {
	private MyStringElement first;
    public MyString(char[] data){
        first = new MyStringElement(data);
    }

    public int length(){
        int length = 0;
        MyStringElement tmp = first;
        while (tmp != null){
            length += tmp.getData().length;
            tmp = tmp.getNext();
        }
        return length;
    }

    public void concat(char[] data){
        first.concat(data);
    }

    public String toString(){
        int length = length();
        char[] completeData = new char[length];
        MyStringElement tmp = first;



        int index = 0;
        while (tmp != null){
            for (int i = 0; i < tmp.getData().length; i++) {
                completeData[index++] = tmp.getData()[i];
            }
            tmp = tmp.getNext();
        }


        return new String(completeData);
    }


    public boolean equals(MyString other) {
        if(other == null) {
            return false;
        }
        int thisLength = length();
        int otherLength = other.length();

        if(thisLength != otherLength) return false;

        MyStringElement tmp = first;
        MyStringElement tmpThere = other.first;

        int index = 0;
        while (tmp != null && tmpThere != null){
            for (int i = 0; i < tmp.getData().length; i++) {
                if(tmp.getData()[i] != tmpThere.getData()[i]){
                    return false;
                }
            }
            tmp = tmp.getNext();
            tmpThere = tmpThere.getNext();
        }

        return true;
    }

    public int indexOf(char c){
        MyStringElement tmp = first;


        int index = 0;
        while (tmp != null){
            for (int i = 0; i < tmp.getData().length; i++) {
                if(tmp.getData()[i] == c){
                    return index;
                }
                index++;
            }
            tmp = tmp.getNext();
        }

        return -1;
    }

    public int lastIndexOf(char c){
        MyStringElement tmp = first;


        int counter = 0;
        int index = -1;
        while (tmp != null){
            for (int i = 0; i < tmp.getData().length; i++) {
                if(tmp.getData()[i] == c){
                   index = counter;
                }
                counter++;
            }
            tmp = tmp.getNext();
        }

        return index;
    }
}
