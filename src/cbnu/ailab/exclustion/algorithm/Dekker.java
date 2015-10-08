package exclusion;

public class Dekker{
	public static int favoredThread = 1;
	public static boolean Thread_1_wants_to_enter = false;
	public static boolean Thread_2_wants_to_enter = false;

	public static class ThreadT1 extends Thread{
		public void run(){
			while(true){
				Thread_1_wants_to_enter = true;
				while(Thread_2_wants_to_enter){
					System.out.println("쓰레드 1번이 임계영역에 접근");
					try {
						Thread.sleep((long) ((Math.random()*1000)+500));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(favoredThread == 2){
						Thread_1_wants_to_enter = false;
						while(favoredThread == 2);
						Thread_1_wants_to_enter = true;
					}
					System.out.println("쓰레드 1번이 임계영역 탈충");
				}

				//critical section code
				favoredThread = 2;
				Thread_1_wants_to_enter = false;

				// code outside critical section
				
			}
		}
	}
	
	public static class ThreadT2 extends Thread{
		public void run(){
			while(true){
				Thread_2_wants_to_enter = true;
				while(Thread_1_wants_to_enter){
					System.out.println("쓰레드 2번이 임계영역에 접근");
					try {
						Thread.sleep((long) ((Math.random()*1000)+500));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(favoredThread == 1){
						Thread_2_wants_to_enter = false;
						while(favoredThread == 1);
						Thread_2_wants_to_enter = true;
					}
					System.out.println("쓰레드 2번이 임계영역 탈충");
				}

				//critical section code
				
				favoredThread = 1;
				Thread_2_wants_to_enter = false;

				// code outside critical section
				
			}
		}
	}
	public static void main(String[] argc) throws Exception{
		Dekker.ThreadT1 T1 = new Dekker.ThreadT1();
		Dekker.ThreadT2 T2 = new Dekker.ThreadT2();
		T1.start();
		T2.start();
		Thread.sleep(20000);
		T1.stop();
		T2.stop();
	}
}
