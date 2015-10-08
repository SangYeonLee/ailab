package exclusion;

public class Peterson {
	public static int favoredThread=1;
	public static boolean t1WantsToEnter=false;
	public static boolean t2WantsToEnter=false;
	public static class ThreadT1 extends Thread {
		public void run() {
			while(true) {
				t1WantsToEnter = true;
				favoredThread = 2;

				while(t2WantsToEnter && favoredThread == 2);
				//�Ӱ� ���� �ڵ�
				System.out.println("t1�� ���Խ��ϴ�");
				try{
					Thread.sleep((int)(Math.random() * 4001+1000));
				} catch(InterruptedException exception){}
				System.out.println("t1�� �����̴ϴ�");
				t1WantsToEnter = false;
				//�Ӱ� ���� ���� �ڵ�
			}
		}
	}
	public static class ThreadT2 extends Thread {
		public void run() {
			while(true) {
				t2WantsToEnter = true;
				favoredThread = 1;

				while(t1WantsToEnter && favoredThread == 1);
				//�Ӱ� ���� �ڵ�
				System.out.println("t2�� ���Խ��ϴ�");
				try{
					Thread.sleep((int)(Math.random() * 4001+1000));
				} catch(InterruptedException exception){}
				System.out.println("t2�� �����̴ϴ�");
				t2WantsToEnter = false;
				//�Ӱ� ���� ���� �ڵ�
			}
		}
	}
	public static void main(String[] args) {
		ThreadT1 t1 = new ThreadT1();
		ThreadT2 t2 = new ThreadT2();
		t1.start();
		t2.start();
	}
}