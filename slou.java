import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Object;


public class SLOU {
	static double [][]A1;
	static double [][]LU;
	static int [] [] P;
	static double [] b={3,2,10};
	static int []p;
	static int n;
	static int det=1;
	public static void main(String argv []){
		try {
			double [][]A = new double [3][3];
			Massiv();
			step1();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		A1= new double[n][n];
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
					A1[q][j]=mas[i];
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
	   public static void swapRow(double arr[][], int n, int k) {
	   double tmp[] = new double[arr[n].length];
	        System.arraycopy(arr[n], 0, tmp, 0, arr[n].length);
	        System.arraycopy(arr[k], 0, arr[n], 0, arr[k].length);
	        System.arraycopy(tmp, 0, arr[k], 0, tmp.length);
	    }
	
public static void step1() throws FileNotFoundException, IOException{
	int j=0,sos,d=0,k1;
	double[][] U,L,C;
	U= Arrays.copyOf(Massiv(), n);
	L= Arrays.copyOf(Massiv(), n);
	
	 P=new int[n][n];
	double h;
	p=new int[n];
	L=new double[n][n];
	C=new double[n][n];
	for(int i=0;i<n;i++){
		p[i]=i+1; //0 1 2
	}
	for(int i=0;i<n;i++){
		for(j=0;j<n;j++){
			System.out.println(U[i][j]);
		}
		}
	//каждый столбец
	for(j=0;j<n-1;j++){
		int num=j;
		double max=Math.abs(U[j][j]);

		for(int i=j+1;i<n;i++)
	//метод поиска max элемента
		if(max<Math.abs(U[i][j])){
			num=i;
			max=Math.abs(U[i][j]);
			System.out.println("max="+max);
		}
	
		if(num!=j){
			System.out.println("j="+j);
			swapRow(U,num,j);
			swapRow(L,num,j);
			//swapRow(L,num,j);
			System.out.println("num"+num);
			System.out.println("elem="+U[num][j]);
	//P-единичная м/атрица
		sos=p[num];
		p[num]=p[j];
		p[j]=sos;
	//b
		h=b[num];
		b[num]=b[j];
		b[j]=h;
		
		det = -det;//перестановка строк(определение + или - для определителя)
		}
		for(int k=j+1;k<n;k++){
			System.out.println(U[k][j]+" "+U[j][j]);
					  L[k][j] =U[k][j]/ U[j][j];
					  System.out.println("деление:"+L[k][j]);
		}
		for(int i=j+1;i<n;i++){
			for(int k=j;k<n;k++){
				     U[i][k] -= L[i][j] * U[j][k];
				      System.out.println(i+""+k+"вычитание:"+U[i][k]);
				      
			}
		}	
	}
	LU=new double [3][3];
	for(int i=0;i<n;i++){
		for(j=0;j<n;j++){
			LU[i][j]=L[i][j]+U[i][j];
		}
		}
	for(int i=0;i<n;i++){
	L[i][i]=1;
	}
	for(int i=0;i<n;i++){
		System.out.println("p="+p[i]);
	}
	for(int i=0;i<n;i++){
		P[i][p[i]-1]=1;
		}
	
	System.out.println("проверка (произведение)="+prov(P,U,L));
	System.out.println("определитель="+MatrixDeterminant(U));
	System.out.println("вектор:"+Ssolve(Massiv(),L,U, b,P));
	InverseMatrix(Massiv(),L,U,A1,P);
	for(int i=0;i<n;i++){
		for(j=0;j<n;j++){
			//System.out.println(i+" " + j+P[i][j]);
		System.out.println(i+" "+j+" "+"элемент LU="+LU[i][j]);
		//System.out.println(i+" "+j+" "+"элемент A="+Massiv()[i][j]);
		}
	}
	}
public static boolean prov(int [][]E, double [][]U,double [][] L) throws FileNotFoundException, IOException{
	boolean result=true;
	double [][] C=new double[3][3];
	double [][] D=new double[3][3];
	double [][] R=new double[3][3];
	double [][] T=new double[3][3];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			for(int h=0;h<n;h++){
			D[i][h]=(E[i][h]*Massiv()[h][j]);
			C[i][j]+=D[i][h];
		}
			
		}
	}
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			for(int h=0;h<n;h++){
			R[i][h]=(L[i][h]*U[h][j]);
			T[i][j]+=R[i][h];
		}
			
		}
	}for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
	if(C[i][j]!=T[i][j]){
		result=false;
	}
		}
	}

	return result;
}

static double MatrixDeterminant(double[][] matrix)

{
  double result =det;
  for (int i = 0; i <matrix.length; ++i)
    result *= matrix[i][i];
  return result;
}

static double [] Ssolve(double [][]A,double [][]L,double[][]U,double[]b,int[][] P){
	double[]y=new double[n];
	double []x=new double [n];
	double []Pb = new double[n];
	double [][] R = new double[n][n];
	double []T=new double[n];
	double [] Q=new double[n];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			R[i][j]=(P[i][j]*b[j]);
			Pb[i]+=R[i][j];
		}
	}
			for(int i=0;i<n;i++){
			System.out.println("Pb="+Pb[i]);
				}
	double sum=0;
	//Ly=Pb
	for(int i=0;i<n;i++){
		sum=0;
		for (int j=0;j<i;j++){
			sum+=L[i][j]*y[j];
		}
			y[i]=Pb[i]-sum;
			System.out.println("i="+i+" "+y[i]);
		
	}
	sum=0;
	double result;
	for(int i=2;i>-1;i--){
		sum=0;
		for (int j=i+1;j<n;j++){
			sum+=U[i][j]*x[j];
		}
			x[i]=(y[i]-sum)/U[i][i];
			System.out.println("x="+x[i]);
	}
	System.out.println(x[0]+" "+x[1]+" "+x[2]);
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			Q[i]=A[i][j]*x[j];
			T[i]+=Q[i];
		}
	}
		for(int i=0;i<n;i++){
			result=T[i]-b[i];
			System.out.println("result="+result);
			}
	return x;
}
static double rang(double [][]A){
	double[][] U=A,L;
	L=new double[3][3];
	int n=A.length;
	double result=0;
	int sos;
	for(int j=0;j<n-1;j++){
		double num=0;
		double max=Math.abs(U[j][j]);

		for(int i=j+1;i<n;i++)
	//метод поиска max элемента
		if(max<Math.abs(U[i][j])){
			num=max;
			max=Math.abs(U[i][j]);
			swapRow(U,i,j);
			System.out.println("num"+num);
			System.out.println("elem="+U[i][j]);
		}
		for(int k=j+1;k<n;k++){
			System.out.println(U[k][j]+" "+U[j][j]);
					 L[k][j] =U[k][j]/ U[j][j];
					  System.out.println("деление:"+L[k][j]);
		}
		for(int i=j+1;i<n;i++){
			for(int k=j;k<n;k++){
				     U[i][k] -= L[i][j] * U[j][k];
				      System.out.println(i+""+k+"вычитание:"+U[i][k]);
				      
			}
		}
		
	}
	return result;
	}

static void InverseMatrix(double [][]A, double [][] L,double [][]U,double [][]A1,int [][]P){
	int i,j;
	double sum;
	double[][] res=new double[3][3];
	double [] y=new double [n];
	double [][] x=new double [n][n];
	int [][] e=new int[n][n];
	for(i=0;i<n;i++){
		for(int k=0;k<3;k++)
		e[i][k]=P[p[i]-1][k];
	}
	
	for(i=0;i<n;i++){
		for(int k=0;k<3;k++)
		System.out.println("e="+i+" "+e[i][k]);
	}
	for(int k=0;k<n;k++){
	//Ly=ek;
	for(i=0;i<n;i++){
		sum=0;
		
		for (j=0;j<i;j++){
			sum+=L[i][j]*y[j];
		}
			y[i]=e[k][i]-sum;
			System.out.println("i="+i+" "+y[i]);
		
	}
	
	//Ux=yi;
	sum=0;

	for(i=2;i>-1;i--){
		sum=0;
		for (j=i+1;j<n;j++){
			sum+=U[i][j]*x[j][k];
		}
			x[i][k]=(y[i]-sum)/U[i][i];
			System.out.println("x="+i+k+" "+x[i][k]);
	}
	
	
	}
		for ( i = 0; i < n; i++) {
            for ( j = 0; j <n; j++) {
                for (int k = 0; k < n; k++) {
                    res[i][j] +=A1[i][k] *P[k][j]; 
                }
            }
        }

		for(int t=0; t<n;t++){
			for(int q=0;q<n;q++){
				System.out.println("проверка"+res[t][q]);
			}
		}
}
}
