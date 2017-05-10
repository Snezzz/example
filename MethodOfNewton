public class ResolveOfSystem {

	static double [] value=new double[10];
	static double []X=new double[10];
	static int n=10;
	static int det=1;
     static int k=-1;
	
public static void methodOfNewton(){	
	double []current=new double [10];
	double [] firstValue;
	double []x=new double [10];
	long timeee;
	int step;
	int count=0; //число итераций
	double E=1e-6;
//x(0)
    firstValue=new double [10];
	firstValue[0]=0.5;
	firstValue[1]=0.5;
	firstValue[2]= 1.5;
	firstValue[3]=-1.0;
	firstValue[4]=-0.5;
	firstValue[5]=1.5;
	firstValue[6]=0.5;
	firstValue[7]=-0.5;
	firstValue[8]=1.5;
	firstValue[9]=-1.5;
	
  	Date date=new Date();
  timeee=date.getTime();
		X=Arrays.copyOf(firstValue, n);
	
		Timer t = new Timer(true);
		
		
   while(true){
	   double[][] cg=new double[10][10];
	   double []f=new double[10];
	   matrixOfJacoby(cg,X);
	   func(f,X);
    	//LU разложение для решения СЛАУ W(xk)*delX=-F(xk) + решение deX
	   x=LU(cg,f);
    	for(int i=0;i<n;i++){
    	     current[i]=X[i]-x[i]; //fx(k+1)=fx{k)-deX
    	}
    	for(int i=0;i<n;i++){
	    	// System.out.println("function:"+current[i]);
    	}
    	double error=0.0;
   //главное условие ||xk+1-xk||
        for (int i = 0; i < n; i++) {
        	if (Math.abs(current[i] - X[i]) > error){
                error = Math.abs(current[i] - X[i]);
        	}
        }
        // Если необходимая точность достигнута, то завершаем
        if (error <  E) {
       	 System.out.println("error<<<E="+error);
    	 System.out.println("Решение системы с точностью E= :"+E);
    	    for(int q=0;q<n;q++){
    	    	System.out.println(current[q]);
    	    }
          break;
     }
        //иначе ищем следующий
       count=count+1;
       X=Arrays.copyOf(current,n); //текущее значениие
      
    }
    
}
public static double [] LU(double [][]W,double []F) {
	int j=0,sos,forrang=0;
	double[][] U,L;
	double[][] LU;
	double [] deX=new double [10];
	int[][] P;
	U= Arrays.copyOf(W, n);
	L= Arrays.copyOf(W, n);
	P=new int[n][n];
	double h=0;
	int[] p=new int[n];
	L=new double[n][n];
	for(int i=0;i<n;i++){
		p[i]=i+1; //0 1 2
	}
	System.out.println("матрица U:");
	for(int i=0;i<n;i++){
		for(j=0;j<n;j++){
	//		System.out.println(U[i][j]);
		}
		}
	//каждый столбец
	for(j=0;j+forrang<n;j++){
		int num=j;
		double max=Math.abs(U[j][j+forrang]);

		for(int i=j+1;i<n;i++)
	//метод поиска max элемента
		if(max<Math.abs(U[i][j])){
			num=i;
			max=Math.abs(U[i][j+forrang]);
			//System.out.println("max="+max);
		}
		if(max<1e-13){ //при обнулении находим ранг
			forrang++;
			j--;
			continue;// досрочно переходим к соедующей итерации
		}
		if(num!=j){
			swapRow(U,num,j);
			swapRow(L,num,j);
			//swapRow(L,num,j);
			//System.out.println("num"+num);
			//System.out.println("elem="+U[num][j]);
	//P-единичная м/атрица
		sos=p[num];
		p[num]=p[j];
		p[j]=sos;
	//b
		h=F[num];
		F[num]=F[j];
		F[j]=h;
		for(int i=0;i<n;i++){
	//		System.out.println("F="+F[i]);
		}
		det = -det;//перестановка строк(определение + или - для определителя)
		}
		for(int k=j+1;k<n;k++){
					  L[k][j] =U[k][j+forrang]/ U[j][j+forrang];
					 // System.out.println("деление:"+L[k][j]);
		}
		for(int i=j+1;i<n;i++){
			for(int k=j+forrang;k<n;k++){
				     U[i][k] -= L[i][j] * U[j][k];
				     // System.out.println(i+""+k+"вычитание:"+U[i][k]);
				      
			}
		}
	}

	LU=new double [n][n];
	for(int i=0;i<n;i++){
		for(j=0;j<n;j++){
			LU[i][j]=L[i][j]+U[i][j];
		}
		}
	
	System.out.println("матрица LU:");
	for(int i=0;i<n;i++){
		for(j=0;j<n;j++){
		//	System.out.println(LU[i][j]);
		}
		}
	for(int i=0;i<n;i++){
	L[i][i]=1;
	}
	
	for(int i=0;i<n;i++){
		P[i][p[i]-1]=1;
		//System.out.println(i+" "+(p[i]-1));
		}
	for(int i=0;i<n;i++){
		for(int q=0;q<n;q++){
			//System.out.println("матрица P="+P[i][q]);
		}
	}
	
	deX=Ssolve(W,L,U,F,P);//дельта х
	
	return deX;
}
	 public static void swapRow(double arr[][], int n, int k) {
		   double tmp[] = new double[arr[n].length];
		        System.arraycopy(arr[n], 0, tmp, 0, arr[n].length);
		        System.arraycopy(arr[k], 0, arr[n], 0, arr[k].length);
		        System.arraycopy(tmp, 0, arr[k], 0, tmp.length);
		    }
static double []Ssolve(double [][]A,double [][]L,double[][]U,double[]b,int[][] P){
			double sum=0;
			double[]y=new double[n];
			double []x=new double [n];
			double []Pb = new double[n];
			double []T=new double[n];
			for(int i=0;i<n;i++){
				sum=0;
				for(int j=0;j<n;j++){
				//	System.out.println("матрица b="+b[j]);
					sum+=P[i][j]*b[j];
				}
				Pb[i]=sum;
				System.out.println(sum);
			}
					for(int i=0;i<n;i++){
					//System.out.println("Pb="+Pb[i]);
						}
			
			//Ly=Pb
			for(int i=0;i<n;i++){
				sum=0;
				for (int j=0;j<i;j++){
					sum+=L[i][j]*y[j];
				}
					y[i]=Pb[i]-sum;
					//System.out.println("i="+i+" "+y[i]);
				
			}
			sum=0;
			double result=0;
//X МЕНЯТСЯ НЕПРАВИЛЬНО
			for(int i=n-1;i>=0;i--){
				sum=0;
				for (int j=i+1;j<n;j++){
					sum+=U[i][j]*x[j];
				}
					x[i]=(y[i]-sum)/U[i][i];
					
			}
			
			for(int i=0;i<n;i++){
				sum=0;
				for(int j=0;j<n;j++){
					sum+=A[i][j]*x[j];
					
				}
				T[i]=sum;
				//System.out.println("x="+x[i]);
			}
//ЗДЕСЬ ГРОМАДНЫЕ ЧИСЛА
				for(int i=0;i<n;i++){
					result=T[i]-b[i];
					//System.out.println("result="+result);
					}
			return x;
		}		
    
    public static double[][]matrixOfJacoby(double[][]cg,double []X){
	//матрица якоби
			cg[0][0] = -Math.sin(X[0] * X[1]) * X[1];
		    cg[0][1] = -Math.sin(X[0]* X[1]) *X[0];
		    cg[0][2] = 3. * Math.exp(-(3 *X[2]));
		    cg[0][3] =X[4] * X[4];
		    cg[0][4] = 2 * X[3] * X[4];
		    cg[0][5] = -1;
		    cg[0][6] = 0;
		    cg[0][7] = -2. * Math.cosh((2 * X[7])) * X[8];
		    cg[0][8] = -Math.sinh((2 * X[7]));
		    cg[0][9] = 2;
		    cg[1][0] = Math.cos(X[0] * X[1]) *X[1];
		    cg[1][1] = Math.cos(X[0] *X[1]) * X[0];
		    cg[1][2] = X[8] * X[6];
		    cg[1][3] = 0;
		    cg[1][4] = 6 * X[4];
		    cg[1][5] = -Math.exp(-X[9]+ X[5]) - X[7] - 0.1e1;
		    cg[1][6] = X[2] * X[8];
		    cg[1][7] = -X[5];
		    cg[1][8] = X[2] * X[6];
		    cg[1][9] = Math.exp(-X[9] +X[5]);
		    cg[2][0] = 1;
		    cg[2][1] = -1;
		    cg[2][2] = 1;
		    cg[2][3] = -1;
		    cg[2][4] = 1;
		    cg[2][5] = -1;
		    cg[2][6] = 1;
		    cg[2][7] = -1;
		    cg[2][8] = 1;
		    cg[2][9] = -1;
		    cg[3][0] = -X[4] * Math.pow(X[2] + X[0], -2.);
 		    cg[3][1] = -2. * Math.cos(X[1] *X[1]) * X[1];
		    cg[3][2] = -X[4] * Math.pow(X[2] + X[0], -2.);
		    cg[3][3] = -2. * Math.sin(-X[8] + X[3]);
		    cg[3][4] = 1. / (X[2] + X[0]);
		    cg[3][5] = 0;
		    cg[3][6] = -2. *Math.cos(X[6] * X[9]) * Math.sin(X[6] * X[9]) * X[9];
		    cg[3][7] = -1;
		    cg[3][8] = 2. * Math.sin(-X[8] + X[3]);
		    cg[3][9] = -2. * Math.cos(X[6] * X[9]) * Math.sin(X[6] * X[9]) * X[6];
		    cg[4][0] = 2 * X[7];
		    cg[4][1] = -2. * Math.sin(X[1]);
		    cg[4][2] = 2 * X[7];
		    cg[4][3] = Math.pow(-X[8] + X[3], -2.);
		    cg[4][4] = Math.cos(X[4]);
		    cg[4][5] = X[6] * Math.exp(-X[6] * (-X[9] + X[5]));
		    cg[4][6] = -(X[9] - X[5]) * Math.exp(-X[6] * (-X[9]+ X[5]));
		    cg[4][7] = (2 * X[2]) + 2. *X[0];
		    cg[4][8] = -Math.pow(-X[8] + X[3], -2.);
		    cg[4][9] = -X[6] * Math.exp(-X[6] * (-X[9] +X[5]));
		    cg[5][0] = Math.exp(X[0] - X[3] - X[8]);
		    cg[5][1] = -3. / 2. * Math.sin(3. * X[9] * X[1]) * X[9];
		    cg[5][2] = -X[5];
		    cg[5][3] = -Math.exp(X[0] - X[3] - X[8]);
		    cg[5][4] = 2 * X[4] / X[7];
		    cg[5][5] = -X[2];
		    cg[5][6] = 0;
		    cg[5][7] = -X[4] *X[4]*  Math.pow(X[7], (-2));
		    cg[5][8] = -Math.exp(X[0] - X[3] -X[8]);
		    cg[5][9] = -3. / 2. * Math.sin(3. * X[9] * X[1]) * X[1];
		    cg[6][0] = Math.cos(X[3]);
		    cg[6][1] = 3. * X[1] * X[1] *X[6];
		    cg[6][2] = 1;
		    cg[6][3] = -(X[0] - X[5]) * Math.sin(X[3]);
		    cg[6][4] = Math.cos(X[9] / X[4] + X[7]) *X[9] *  Math.pow(X[4], (-2));
		    cg[6][5] = -Math.cos(X[3]);
		    cg[6][6] = Math.pow(X[1], 3.);
		    cg[6][7] = -Math.cos(X[9]/ X[4]+ X[7]);
		    cg[6][8] = 0;
		    cg[6][9] = -Math.cos(X[9] / X[4] + X[7]) /X[4];
		    cg[7][0] = 2. *  X[4] * (X[0] - 2. * X[5]);
		    cg[7][1] = -X[6] * Math.exp(X[1] * X[6] +X[9]);
		    cg[7][2] = -2. * Math.cos(-X[8] + X[2]);
		    cg[7][3] = 0.15e1;
		    cg[7][4] = Math.pow(X[0] - 2. * X[5], 2.);
		    cg[7][5] = -4. *  X[4] * (X[0] - 2. * X[5]);
		    cg[7][6] = -X[1] * Math.exp(X[1] * X[6] + X[9]);
		    cg[7][7] = 0;
		    cg[7][8] = 2. *Math.cos(-X[8] + X[2]);
		    cg[7][9] = -Math.exp(X[1] *X[6] + X[9]);
		    cg[8][0] = -3;
		    cg[8][1] = -2. *  X[7]* X[9] *X[6];
		    cg[8][2] = 0;
		    cg[8][3] = Math.exp((X[4]+ X[3]));
		    cg[8][4] = Math.exp((X[4] + X[3]));
		    cg[8][5] = -0.7e1 * Math.pow(X[5], -2.);
		    cg[8][6] = -2. *X[1] *  X[7] *X[9];
		    cg[8][7] = -2. * X[1]* X[9] * X[6];
		    cg[8][8] = 3;
		    cg[8][9] = -2. * X[1] *  X[7] *X[6];
		    cg[9][0] = X[9];
		    cg[9][1] = X[8];
		    cg[9][2] = -X[7];
		    cg[9][3] = Math.cos(X[3]+ X[4] + X[5]) * X[6];
		    cg[9][4] = Math.cos(X[3]+ X[4] + X[5]) * X[6];
		    cg[9][5] = Math.cos(X[3]+ X[4] + X[5]) * X[6];
		    cg[9][6] = Math.sin(X[3]+ X[4] + X[5]);
		    cg[9][7] = -X[2];
		    cg[9][8] = X[1];   
		    cg[9][9] = X[0];
		    System.out.println("исходная матрица якоби");
		    for(int i=0;i<9;i++)
		    {
		    	for(int j=0;j<9;j++)
		    	{
		    		System.out.println(cg[i][j]);
		    	}
		    }
		    return cg;
}
	
public static double []func(double []f,double[]X){
	
	//исходная система функций
		 f[0] = Math.cos(X[0] * X[1]) - Math.exp(-3 * X[2]) + X[3] * X[4] * X[4] - X[5] - Math.sinh(2 * X[7]) * X[8] + 2 * X[9] + 2.0004339741653854440;
	     f[1] =Math.sin(X[0] * X[1]) + X[2] * X[8] * X[6] - Math.exp(-X[9] + X[5]) + 3 * X[4] * X[4] - X[5] * (X[7] + 1) + 10.886272036407019994;
	     f[2] = X[0] - X[1] + X[2] - X[3] + X[4] - X[5] + X[6] - X[7] + X[8] - X[9] - 3.1361904761904761904;
	     f[3] = 2 * Math.cos(-X[8] + X[3]) + X[4] / (X[2] + X[0]) - Math.sin(X[1] * X[1]) + Math.cos(X[6] * X[9])*Math. cos(X[6] * X[9]) - X[7] - 0.1707472705022304757;
	     f[4] = Math.sin(X[4]) + 2 * X[7] * (X[2] + X[0]) - Math.exp(-X[6] * (-X[9] + X[5])) + 2 * Math.cos(X[1]) - 1 / (X[3] - X[8]) - 0.3685896273101277862;
	     f[5] = Math.exp(X[0] - X[3] - X[8]) + X[4] * X[4] / X[7] + Math.cos(3 * X[9] * X[1]) / 2 - X[5] * X[2] + 2.0491086016771875115;
	     f[6] = X[1] * X[1] * X[1] * X[6] - Math.sin(X[9] / X[4] + X[7]) + (X[0] - X[5])*Math.cos(X[3]) + X[2] - 0.7380430076202798014;
	     f[7] = X[4] * (X[0] - 2 * X[5])*(X[0] - 2 * X[5]) - 2 * Math.sin(-X[8] + X[2]) + 1.5*X[3] - Math.exp(X[1] * X[6] + X[9]) + 3.5668321989693809040;
	     f[8] = 7 / X[5] +Math.exp(X[4] + X[3]) - 2 * X[1] * X[7] * X[9] * X[6] + 3 * X[8] - 3 * X[0] - 8.4394734508383257499;
	     f[9] = X[9] * X[0] + X[8] * X[1] - X[7] * X[2] + Math.sin(X[3] + X[4] + X[5])*X[6] - 0.78238095238095238096;
	//частные производные для матрицы Якоби
	 //для -F(Xk)
	    
	     for(int q=0;q<n;q++){
	    	 f[q]=-f[q];
	     }
	     for(int q=0;q<n;q++){
	    	 //System.out.println("матрица f"+f[q]);
	     }
	     return f;
}
public static void main(String[] args) {
		// TODO Auto-generated method stub
	System.out.println("МЕТОД НЬЮТОНА");
		methodOfNewton();
    }
    }
