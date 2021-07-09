package classes;

import java.util.Random;

public class OTP {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String otp=generateOTP(6);
//		System.out.println("Generated OTP: "+otp);
//
//	}
	
	public static String generateOTP() {
		Random random=new Random();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<5;i++) {
			sb.append(random.nextInt(9));
		}
		return sb.toString();
	}

}
