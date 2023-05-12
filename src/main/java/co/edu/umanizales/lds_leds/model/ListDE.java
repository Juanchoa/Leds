package co.edu.umanizales.lds_leds.model;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

@Data
public class ListDE {
    private NodeDE head;
    private int size;


    public int checkId(int id){
        if(head!=null) {
            NodeDE temp = this.head;
            int value = 0;
            while (temp != null) {

                if (temp.getData().getIdentification() == id) {
                    value = 1;
                    return value;
                    //led existe
                }
                temp = temp.getNext();
            }
            return value;
            //led no exite
        }
        return 0;
    }

    public void addLetToStart(Led led){
        NodeDE temp = new NodeDE(led);
        if(checkId(led.getIdentification())==1){
            return;
            //ya existe, no se puede agregar
        }
        else{
            if(head==null){
                head=temp;
                size++;
            }
            else{
              temp.setNext(head);
              head.setPrevious(temp);
              head=temp;
              size++;
            }
        }

    }
    public void addLetToEnd(Led led){
        NodeDE temp1= new NodeDE(led);
        if(head==null){
            head=temp1;
        }
        else{
            NodeDE temp= head;
            while (temp.getNext()!=null){
                if(temp.getData().getIdentification()== led.getIdentification()){
                    //el pelao ya existe
                    return;
                }
                temp=temp.getNext();
            }
            if(temp.getData().getIdentification()== led.getIdentification()){
                //el pelao ya existe
                return;
            }
            temp.setNext(temp1);
            temp1.setPrevious(temp);
            size++;
        }
    }
    public List<Led> seeLeds(){
        List<Led> ledList = new ArrayList<>();
        NodeDE temp=this.head;
        while (temp!=null){
            ledList.add(temp.getData());

            temp=temp.getNext();
        }
        return ledList;
    }
    public void resetLeds(){
        if(head==null){
            //no hay datos
            return;
        }
        else{
            NodeDE temp = this.head;
            while(temp!=null){
                temp.getData().setState(false);
                temp.getData().setDateOn(null);
                temp.getData().setDateOff(null);
                temp=temp.getNext();
            }
        }
    }
    public int seeSize(){
        return size;
    }


    public void turnOnLedsFromTheMiddleToExtremes() throws InterruptedException {

        if(size==1){
            return;    //no de pueden prender los extremos
        }
        if(size%2==0){//la cantidad de datos es par
            int position = (size/2); //como es par simpre me va a dar natural
            NodeDE temp = this.head;
            //un while para que temp llegue hasta la posición de la mitad
            int i=1;
            while(i<position) {
                temp = temp.getNext();
                i++;
            }
            //creamos los 2 nodos de la mitad al tener una lista de tamaño par
            NodeDE temp2= temp.getNext();
            //actualizamos los datos de los leds de la mitad
            temp.getData().setState(true);
            temp2.getData().setState(true);
            temp.getData().setDateOn(LocalTime.now());
            temp2.getData().setDateOn(LocalTime.now());
            Thread.sleep(1000); //función para que pase un segundo
            temp.getData().setState(false);
            temp2.getData().setState(false);
            temp.getData().setDateOff(LocalTime.now());
            temp2.getData().setDateOff(LocalTime.now());

            NodeDE tempPrev = temp.getPrevious();
            NodeDE tempNext = temp2.getNext();
            while (tempPrev.getPrevious()!=null && tempNext.getNext()!=null){
                //actualizamos los datos de la lsita en cadena
                tempNext.getData().setState(true);
                tempNext.getData().setDateOn(LocalTime.now());
                tempPrev.getData().setState(true);
                tempPrev.getData().setDateOn(LocalTime.now());
                Thread.sleep(1000);
                tempNext.getData().setState(false);
                tempNext.getData().setDateOff(LocalTime.now());
                tempPrev.getData().setState(false);
                tempPrev.getData().setDateOff(LocalTime.now());

                tempNext=tempNext.getNext();
                tempPrev=tempPrev.getPrevious();
            }
            //parados en el ultimo, dejamos ensendidos los leds
            tempNext.getData().setState(true);
            tempPrev.getData().setState(true);
            tempNext.getData().setDateOn(LocalTime.now());
            tempPrev.getData().setDateOn(LocalTime.now());
        }
        else{//la cantidad de datos es impar
            //formula para sacar la posicion exacta de la mitad de la lista, siempre dará entero
            int position = (size-((size-1)/2));
            NodeDE temp = this.head;
            //un while para que temp llegue hasta la posición exacta de la mitad
            int i=1;
            while(i<position) {
                temp = temp.getNext();
                i++;
            }
            //actualizamos los datos de el dato exacto de la mitad
            temp.getData().setState(true);
            temp.getData().setDateOn(LocalTime.now());
            Thread.sleep(1000);
            temp.getData().setState(false);
            temp.getData().setDateOff(LocalTime.now());

            //creamos dos nodos para poder hacer la misma cadena en toda la lsita
            NodeDE tempPrev = temp.getPrevious();
            NodeDE tempNext = temp.getNext();

            while (tempPrev.getPrevious()!=null && tempNext.getNext()!=null){
                //actualizamos los datos de la lsita en cadena
                tempNext.getData().setState(true);
                tempNext.getData().setDateOn(LocalTime.now());
                tempPrev.getData().setState(true);
                tempPrev.getData().setDateOn(LocalTime.now());
                Thread.sleep(1000);
                tempNext.getData().setState(false);
                tempNext.getData().setDateOff(LocalTime.now());
                tempPrev.getData().setState(false);
                tempPrev.getData().setDateOff(LocalTime.now());

                tempNext=tempNext.getNext();
                tempPrev=tempPrev.getPrevious();
            }
                //parados en el ultimo, dejamos ensendidos los leds
                tempNext.getData().setState(true);
                tempPrev.getData().setState(true);
                tempNext.getData().setDateOn(LocalTime.now());
                tempPrev.getData().setDateOn(LocalTime.now());
        }

    }


}
