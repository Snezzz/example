import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class exersice2 {
	static int n;
	static double [] b={1,2,5};
	static double [][]R=new double[n][n];
	static double [][]Q=new double[n][n];
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Massiv();
		MethodQR(b,Massiv(),R,Q);
		// TODO Auto-generated method stub

	}
	public static double[][] Massiv() throws FileNotFoundException, IOException{
		int [] mas;
		int i=0;
		int h,p=0;
		int [][] ourmas;
		mas=new int[9];
		double [][]A;
		A= new double[3][3];
		n=A.length;
		try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Снежа\\Documents\\A.txt");
            Scanner sc = new Scanner(fis);
            while(sc.hasNext()) {
            mas[i]=sc.nextInt();
           // System.out.println("int = " + mas[i]); 
            i=i+1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		i=0;
		int y=0;
		for(int q=0;q<3;q++){
			for(int j=p;j<3;j++){
				A[q][j]=mas[i];
				i++;
				p=p+3;
				if(p==9){
				p=0;
				}
				if((i==2)||(i==5)||(i==8))
				{	j=j+1;
					A[q][j]=mas[i];
				i++;
				p=0;
				
					}
			}
		}
		for(int q=0;q<3;q++){
			for(int j=p;j<3;j++){
				//System.out.print(A[q][j]);
			}
			
				
		}
			return A;
			}
public static void MethodQR(double b[],double [][]A,double [][]R,double [][]Qmas) throws FileNotFoundException, IOException{
	double [][]c;
	double [][]s;
	R=Arrays.copyOf(Massiv(), n);
	int h=0,h1=0; //счетчик
	double []x=new double[n];
	double sum;
	double [][]Q=new double[n][n];
	Qmas=new double[n][n];
	double [][]T1=new double[n][n];
	double [][]T2=new double[n][n];
	double [][]T3=new double[n][n];
	double [][]result=new double[n][n*3];
	double Akk,Akl,Alk,All,bk,bl;
	c=new double[n][n];
	s=new double[n][n];
	for(int k=0;k<n-1;k++){
		for(int l=k+1;l<n;l++){
			double [][] T=new double[n][n];
			for(int i=0;i<n;i++){
					T[i][i]=1;
				}
			//элементы T
			c[k][l]=R[k][k]/(Math.sqrt(R[k][k]*R[k][k] +A[l][k]*A[l][k])); //cos для ортог матрицы 
			s[k][l]=R[l][k]/(Math.sqrt(R[k][k]*R[k][k] +A[l][k]*A[l][k])); //sin для ортог матрицы
	if(((c[k][l]*c[k][l])+(s[k][l]*s[k][l]))==1){
		System.out.println("RIGHT");
	}
	System.out.println(c[k][l]+""+s[k][l]);
			T[k][k]=c[k][l];
		T[k][l]=-s[k][l];
		T[l][k]=s[k][l];
		T[l][l]=c[k][l];
		
			for(int j=0;j<n;j++){
						for(int a=0;a<n;a++){
			result[l+k-1][h1]=T[j][a];
			System.out.println("K="+k+"j="+j+"a="+a+" "+result[l+k-1][h1]);
			h1=h1+1;
			
		}
					}
			h1=0;		
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.println("T="+T[i][j]);
			}
		}
		System.out.println(".");
		//новые значения матрицы А
			Akk=R[k][k];
			Akl=R[k][l];
			Alk=R[l][k];
			All=R[l][l];
	
			//умножаем исходную матрицу на ортогональную Tkl
			R[k][k]=Akk*c[k][l]+Alk*s[k][l];
			R[k][l]=Akl*c[k][l]+All*s[k][l];
			R[l][k]=-Akk*s[k][l]+Alk*c[k][l];
			R[l][l]=-Akl*s[k][l]+All*c[k][l];

		//умножаем вектор свободных членов b на ортогональную Tkl
			bk=b[k];
			bl=b[l];
			b[k]=bk*c[k][l]+bl*s[k][l];
			b[l]=-bk*s[k][l]+bl*c[k][l];
		}
		
		
	}
//получаем верхнетрегольную матрицу
	for(int k=0;k<n;k++){
		for(int l=0;l<n;l++){
		System.out.println("QR разложение="+R[k][l]);
		
		}	
	}

	// Обратный ход для решения системы Ax=b
	for(int i=n-1;i>=0;i--){
		sum=0;
		for (int j=i+1;j<n;j++){
			sum+=R[i][j]*x[j];
		}
			x[i]=(b[i]-sum)/R[i][i];
		
	}
	  for(int k=0;k<n;k++){
		  System.out.println("x="+x[k]);
	  }
	  for(int i=0;i<n;i++){
			for(int j=0;j<n*3;j++){
				System.out.println("матрица из T="+result[i][j]);
			}
		}
	  h=0;
//делим на три матрицы для дальнейшего перемножения
	  for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				T1[i][k]=result[0][k+h];
				T2[i][k]=result[1][k+h];
				T3[i][k]=result[2][k+h];
			}
			h=h+3;
			}
//Q=T1*T2*T3
	  for(int q=0;q<n;q++){
	  for(int i=0;i<n;i++){
		  sum=0;
		  for(int j=0;j<3;j++){
			  sum+=T1[q][j]*T2[j][i];
		  }
		  Q[q][i]=sum;
	  }
	  }

	  for(int q=0;q<n;q++){
		  for(int i=0;i<n;i++){
			  sum=0;
			  for(int j=0;j<3;j++){
				  sum+=Q[q][j]*T3[j][i];
			  }
			  Qmas[q][i]=sum;
		  }
		  }
	  for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				System.out.println(Qmas[i][k]);
			}
			}

	  test(R,Qmas);
			
	  
}
//A=QR
	public static boolean test(double [][]R,double[][]Q) throws FileNotFoundException, IOException{
		boolean result=true;
		int sum=0;
		double [][] A1=new double [n][n];
		
		 for(int q=0;q<n;q++){
			  for(int i=0;i<n;i++){
				  sum=0;
				  for(int j=0;j<3;j++){
					  sum+=Q[q][j]*R[j][i];
				  }
				 A1[q][i]=sum;
			  }
			  }
		 for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					System.out.println(A1[i][j]);
				}
				}
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(A1!=Massiv()){
					result=false;
					System.out.println("s");
			
		}
			}
		}
		return result;
	}
