package com.pcsetting.example;

public class HugeInteger {
		private int[] digitarray;
		final static int maxsize = 40;
		public HugeInteger() {
			digitarray= new int[maxsize];
		}
		public HugeInteger(String word) {
			this();
			this.parse(word);
		}
		void parse(String word) {
			if(0<word.length() && maxsize>=word.length()) {
				int key=0;
				if(word.charAt(0)=='-') {
					key=1;
				}
				else if(Character.isDigit(word.charAt(0))) {
					for(int j=0;j<maxsize-word.length();j++) {
						digitarray[j]=0;
					}
					digitarray[maxsize-word.length()]=Character.getNumericValue(word.charAt(0));
				}
				else {
					System.out.println("Error: parse's characters must be digit");
				}
				for(int i=1;i<word.length();i++) {
					if(Character.isDigit(word.charAt(i))) {
						for(int j=0;j<maxsize-word.length();j++) {
							digitarray[j]=0;
						}
						if(key==1 && i==1) {
							digitarray[maxsize-word.length()+i]=Character.getNumericValue(word.charAt(i))*(-1);
						}
						else
							digitarray[maxsize-word.length()+i]=Character.getNumericValue(word.charAt(i));
					}
					else {
						System.out.println("Error: parse's characters must be digit");
					}
				}
			}
			else if(word.length()==maxsize+1 && word.charAt(0)=='-') {
				for(int i=1;i<word.length();i++) {
					if(Character.isDigit(word.charAt(i))) {
						digitarray[i-1]=Character.getNumericValue(word.charAt(i));
					}
					else {
						System.out.println("Error: parse's characters must be digit");
					}
				}
				digitarray[0]=-1*digitarray[0];
			}
			else {
				System.out.println("Error: parse must contain at most 40 characters");
			}
		}
		public void add(HugeInteger a) {
			int i=0,j=0,key1=0,key2=0,big=0,small=0,sum=0,carry=0;
			while(a.digitarray[i]==0 && i<maxsize) {
				i++;
				if(i==40) {
					i--;
					return;
				}
			}
			if(a.digitarray[i]<0) {
				key2=1;
			}
			while(this.digitarray[j]==0 && i<maxsize) {
				j++;
				if(j==40) {
					j--;
					int k=0;
					while(k<40) {
						this.digitarray[k] = a.digitarray[k];
						k++;
					}
					return;
				}
			}
			if(this.digitarray[j]<0) {
				key1=1;
			}
			if(i<j) {
				big=i;
				small=j;}
			else {
				big=j;
				small=i;}
			if(key1==key2) {
				if(key1==0) {
					int k=0;
					for(k=39;k>=big;k--) {
						sum = this.digitarray[k]+a.digitarray[k]+carry;
						if(sum>=10) {
							carry=1;
							sum=sum-10;
						}
						else carry=0;
						this.digitarray[k]=sum;
					}
					if(k!=-1) {
						this.digitarray[k] = carry;
					}
				}
				else {
					int k=0;
					for(k=39;k>=big;k--) {
						if(k!=small) {
							if(k!=big || (small-big!=1)) sum = this.digitarray[k]+a.digitarray[k]+carry;
							else sum = this.digitarray[k]+a.digitarray[k]-carry;
							if(sum>=10) {
								carry=1;
								sum=sum-10;
							}
							else carry=0;
							this.digitarray[k]=sum;
						}
						else if(small!=big) {
							if(this.digitarray[k]<0) {
								sum = (-1*this.digitarray[k])+a.digitarray[k]+carry;
							}
							else {
								sum = this.digitarray[k]-a.digitarray[k]+carry;
							}
							if(sum>=10) {
								carry=1;
								sum=sum-10;
							}
							else carry=0;
							this.digitarray[k]=sum;
						}
						else {
							sum = this.digitarray[k]+a.digitarray[k]-carry;
							if(sum<=-10) {
								sum = (sum+10)*(-1);
								carry=-1;
							}
							else carry=0;
							this.digitarray[k]=sum;
						}
					}
					if(k!=-1) {
						this.digitarray[k] = -carry;
						this.digitarray[k] = this.digitarray[k]*(-1);
					}
				}
				if(carry==1 && big==0) {
					System.out.println("The result is overflow, HugeInteger only up to 40 digits");
				}
			}//done
			else {
				HugeInteger positive = new HugeInteger(),negative=new HugeInteger();
				if(key2==1) {
					for(int num=0;num<maxsize;num++) {
						positive.digitarray[num]=this.digitarray[num];
						negative.digitarray[num]=a.digitarray[num];
					}
					negative.digitarray[i]= -1*negative.digitarray[i];
					if(i<j) {
						for(int k=39;k>=big;k--) {
							sum = negative.digitarray[k] - positive.digitarray[k] -carry;
							if(sum<0) {
								sum = sum+10;
								carry=1;
							}
							else carry=0;
							negative.digitarray[k]=sum;
						}
						int count=0;
						while(negative.digitarray[count]==0) {
							this.digitarray[count]=0;
							count++;
						}
						this.digitarray[count]=-1*negative.digitarray[count];
						count++;
						while(count<maxsize) {
							this.digitarray[count]=negative.digitarray[count];
							count++;
						}
					}
					else if(i>j) {
						for(int k=39;k>=big;k--) {
							sum = positive.digitarray[k] -negative.digitarray[k] -carry;
							if(sum<0) {
								sum = sum+10;
								carry=1;
							}
							else carry=0;
							positive.digitarray[k]=sum;
						}
						int count=big;
						while(count<maxsize) {
							this.digitarray[count]=negative.digitarray[count];
							count++;
						}
					}
					else {
						for(int k=39;k>=big;k--) {
							sum = positive.digitarray[k] -negative.digitarray[k] -carry;
							if(sum<0) {
								sum = sum+10;
								carry=1;
							}
							else carry=0;
							positive.digitarray[k]=sum;
						}
						int count=big;
						if(positive.isZero()) {
							for(int num=0;num<maxsize;num++) {
								this.digitarray[num]=0;
							}
							return;
						}
						if(carry==1) {
							while(positive.digitarray[count]==0) {
								this.digitarray[count]=0;
								count++;
							}
							this.digitarray[count]=-1*(9-positive.digitarray[count]);
							count++;
							int l=maxsize-1;
							while(positive.digitarray[l]==0) {
								this.digitarray[l]=0;
								l--;
							}
							this.digitarray[l]=10-positive.digitarray[l];
							while(count<l) {
								this.digitarray[count]=9-positive.digitarray[count];
								count++;
							}
							count=big;
							while(this.digitarray[count]==0) {
								count++;
							}
							if(this.digitarray[count]>0) {
								this.digitarray[count]=-1*this.digitarray[count];
							}
						}
						else {
							while(count<maxsize) {
								this.digitarray[count]=positive.digitarray[count];
								count++;
							}
						}
					}
				}
				else {
					for(int num=0;num<maxsize;num++) {
						positive.digitarray[num]=a.digitarray[num];
						negative.digitarray[num]=this.digitarray[num];
					}
					negative.digitarray[j]= -1*negative.digitarray[j];
					if(i>j) {
						for(int k=39;k>=big;k--) {
							sum = negative.digitarray[k] - positive.digitarray[k] -carry;
							if(sum<0) {
								sum = sum+10;
								carry=1;
							}
							else carry=0;
							negative.digitarray[k]=sum;
						}
						int count=0;
						while(negative.digitarray[count]==0) {
							this.digitarray[count]=0;
							count++;
						}
						this.digitarray[count]=-1*negative.digitarray[count];
						count++;
						while(count<maxsize) {
							this.digitarray[count]=negative.digitarray[count];
							count++;
						}
					}
					else if(i<j) {
						for(int k=39;k>=big;k--) {
							sum = positive.digitarray[k] -negative.digitarray[k] -carry;
							if(sum<0) {
								sum = sum+10;
								carry=1;
							}
							else carry=0;
							positive.digitarray[k]=sum;
						}
						int count=big;
						while(count<maxsize) {
							this.digitarray[count]=negative.digitarray[count];
							count++;
						}
					}
					else {
						for(int k=39;k>=big;k--) {
							sum = positive.digitarray[k] -negative.digitarray[k] -carry;
							if(sum<0) {
								sum = sum+10;
								carry=1;
							}
							else carry=0;
							positive.digitarray[k]=sum;
						}
						if(positive.isZero()) {
							for(int num=0;num<maxsize;num++) {
								this.digitarray[num]=0;
							}
							return;
						}
						int count=big;
						if(carry==1) {
							while(positive.digitarray[count]==0) {
								this.digitarray[count]=0;
								count++;
							}
							this.digitarray[count]=-1*(9-positive.digitarray[count]);
							count++;
							int l=maxsize-1;
							while(positive.digitarray[l]==0) {
								this.digitarray[l]=0;
								l--;
							}
							this.digitarray[l]=10-positive.digitarray[l];
							while(count<l) {
								this.digitarray[count]=9-positive.digitarray[count];
								count++;
							}
							count=big;
							while(this.digitarray[count]==0) {
								count++;
							}
							if(this.digitarray[count]>0) {
								this.digitarray[count]=-1*this.digitarray[count];
							}
						}
						else {
							while(count<maxsize) {
								this.digitarray[count]=positive.digitarray[count];
								count++;
							}
						}
					}
				}
			}
		}
		public void subtract(HugeInteger a) {
			int i=0;
			HugeInteger b = new HugeInteger();
			for(i=0;i<maxsize;i++) {
				b.digitarray[i]=a.digitarray[i];
			}
			for(i=0;i<maxsize;i++) {
				if(b.digitarray[i]!=0) break;
			}
			if(i==40) {
				i--;
			}
			b.digitarray[i]=-1*b.digitarray[i];
			this.add(b);
		}
		public boolean isEqualTo(HugeInteger a) {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]!=a.digitarray[i]) return false;
			}
			return true;
		}
		public boolean isNotEqualTo(HugeInteger a) {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]!=a.digitarray[i]) return true;
			}
			return false;
		}
		public boolean isGreaterThan(HugeInteger a) {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]>a.digitarray[i]) return true;
			}
			return false;
		}
		public boolean isLessThan(HugeInteger a) {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]<a.digitarray[i]) return true;
			}
			return false;
		}
		public boolean isGreaterThanOrEqualTo(HugeInteger a) {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]<a.digitarray[i]) return false;
			}
			return true;
		}
		public boolean isLessThanOrEqualTo(HugeInteger a) {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]>a.digitarray[i]) return false;
			}
			return true;
		}
		public boolean isZero() {
			for(int i=0;i<maxsize;i++) {
				if(this.digitarray[i]!=0) return false;
			}
			return true;
		}
		public void print() {
			int i=0;
			while(this.digitarray[i]==0 && i<maxsize-1) i++;
			if(i==maxsize-1 && this.digitarray[i]==0) {
				System.out.print(0);
				return;
			}
			while(i<maxsize) {
				System.out.print(this.digitarray[i]);
				i++;
			}
			return;
		}
		public void println() {
			int i=0;
			while(this.digitarray[i]==0 && i<maxsize-1) i++;
			if(i==maxsize-1 && this.digitarray[i]==0) {
				System.out.println(0);
				return;
			}
			while(i<maxsize) {
				System.out.print(this.digitarray[i]);
				i++;
			}
			System.out.println("");
		}
		public String tostring() {
			int count=0;
			String word=""; 
			String result="";
			if(this.isZero()) {
				return "0000000000000000000000000000000000000000";
			}
			while(this.digitarray[count]==0) {
				word=word.concat("0");
				count++;
			}
			if(this.digitarray[count]<0) {
				result=result.concat("-");
				result=result.concat(word);
				String s=Integer.toString(-1*this.digitarray[count]);
				count++;
				result=result.concat(s);
			}
			else result=result.concat(word);
			while(count<maxsize){
				String s=Integer.toString(this.digitarray[count]);
				count++;
				result=result.concat(s);
			}
			return result;
		}
}
