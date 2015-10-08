package exclusion;

public class Lamport {
	public static boolean[] choosing;
	public static int[] ticket;
	public static int maxValue;
	public static void setArray(int i){
		choosing = new boolean[i];
		ticket = new int[i];
		maxValue = i;
	}
	public static class bakery extends Thread{
		private int ticketNum=-1;
		public bakery(int i){
			ticketNum = i;
		}
		public void run(){
			while(true){
				choosing[ticketNum] = true;
				ticket[ticketNum] = maxValue + 1;
				choosing[ticketNum] = false;
				int index = 0;
				int i = ticketNum+(((int)Math.random()*maxValue)%maxValue);
				while(index < maxValue){
					if(i == ticketNum){
						continue;
					}
					/*try {
						Thread.sleep((long) (Math.random()*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					while(choosing[i] != false);
					
					while(ticket[i] != 0 && ticket[i] < ticket[ticketNum]);
					
					if(ticket[i] == ticket[ticketNum] && i < ticketNum){
						while(ticket[i] != 0);
					}
					i=(i+1)%maxValue;
					index++;
				}
				System.out.println(ticketNum+" - 임계 영역");
				ticket[ticketNum] = 0;
				System.out.println(ticketNum+" - 임계 영역 탈출");
			}
		}
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] argc) throws Exception {
		int size = 2;
		bakery[] bThread = new bakery[size];
		setArray(size);
		for(int i = 0; i < size; i++){
			bThread[i] = new bakery(i);
			bThread[i].start();
		}
		Thread.sleep(15000);
		for(int i = 0; i < size; i++){
			bThread[i].stop();
		}
	}
	
}
