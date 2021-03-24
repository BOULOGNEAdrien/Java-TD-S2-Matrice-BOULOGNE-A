/*

 ______  _____ _   _ _     _____ _____  _   _  _____      ___      _      _                _____ _____ _   _   __    ___    ___  
 | ___ \|  _  | | | | |   |  _  |  __ \| \ | ||  ___|    / _ \    | |    (_)              /  ___|_   _| | | | /  |  / _ \  /   | 
 | |_/ /| | | | | | | |   | | | | |  \/|  \| || |__     / /_\ \ __| |_ __ _  ___ _ __     \ `--.  | | | |_| | `| | / /_\ \/ /| | 
 | ___ \| | | | | | | |   | | | | | __ | . ` ||  __|    |  _  |/ _` | '__| |/ _ \ '_ \     `--. \ | | |  _  |  | | |  _  / /_| | 
 | |_/ /\ \_/ / |_| | |___\ \_/ / |_\ \| |\  || |___    | | | | (_| | |  | |  __/ | | |   /\__/ / | | | | | | _| |_| | | \___  | 
 \____/  \___/ \___/\_____/\___/ \____/\_| \_/\____/    \_| |_/\__,_|_|  |_|\___|_| |_|   \____/  \_/ \_| |_/ \___/\_| |_/   |_/ 
               


*/
import Jama.Matrix;

public class Matrice {
    private int nbrLig; 

    private int nbrCol; 

    private double[][] coeffs; 
    
    public class ResGauss{ 
		
		int rang=1;                                 // sous class qui stock la signature et le rang
		
		int signature=1;
		
}
/*
 * Constructeur de la classe Matrice
 * */
    public Matrice(int nlig,int ncol) {  

        this.nbrLig = nlig; 
       this.nbrCol = ncol; 
       this.coeffs = new double[nlig][ncol]; 
        
       for(int i = 0 ; i< ncol*nlig ; i++){
			
			this.coeffs[i/ncol][i%ncol]= 0.0;
			
		}

    } 
/*
 * methode qui definie l'affichage du type matrice
 * */
    public String toString() { 

        String s;
        s = "";
        for (int i = 0; i < this.nbrLig; i++) {
            s = s + "[";
            for (int j = 0; j < this.nbrCol; j++) {
                s = s + String.format("%+4.2E", this.coeffs[i][j]);
                if (j != this.nbrCol - 1) {
                    s = s + " ";
                }

            }
            s = s + "]\n";

        }
        return s;
        /*String res = ""; //version perso

        for(int i = 0 ; i < this.nbrLig ; i ++) { 

            res = res + "["; 

            for(int j = 0 ; j < this.nbrCol ; j ++) { 

                res = res +"|"+this.coeffs[i][j]; 

            } 

            res = res + "]\n"; 

        } 

        return res;*/ 

    } 

    public static void testToString() { 

        Matrice m = new Matrice(4, 5); 

        m.coeffs[0][0] = Math.PI; 

        System.out.println("m = \n"+m); 
                                
                            }
      /* creer la matrice identité de taille n
  * */
    
    public static Matrice identite(int n){
        
        Matrice res = new Matrice(n,n);
        for(int i =0; i<n;i++){
            res.coeffs[i][i]= 1;
        }
        return res ;
    }
    
    public static void testIdentite(){
        
        System.out.println(identite(3));
    }
    
    public static Matrice matTest1(){
        Matrice m = new Matrice(3,3);
        int l=0;
     
        for(int i = 1 ;i<9;i++){
            m.coeffs[i/3][i%3]=i;
            }
        
        return m;
        
    }
    /*renvoi un nombre aleatoire 1 ou 2
     * */
    public static int aleaUnDeux() {
		
		return (int)(Math.random()*2+1);
		
	}
   /*renvoi une de nombre aleatoire 1 et 2 avec un proba pz de 0
     * */
    public static Matrice matAleatoirUnOuDeux(int nl,int nc,double pz){
      
        Matrice m = new Matrice(nl,nc);
      
        for(int i= 0;i<nl*nc;i++){
            
            if(Math.random() < pz){
                
                m.coeffs[i/nc][i%nc]=0;     //pour tout les tableaus on utilise une boucle for et on divise i par le nombre de colone pour obtenir le numero de ligne , puis on a la colone avec le modulo
                
            }
            else{
                 m.coeffs[i/nc][i%nc]=aleaUnDeux();
            }
            
        }
       
     return m;
    }
    public static void testAleatoirUnOuDeux(){
        
        System.out.println(matAleatoirUnOuDeux(3,3,0.5));
    }
    /*renvoi une Matrice vecteur à partir d'un tableau
     * */
    public static Matrice creeVecteur(double[] tab ){ 
		
		Matrice res = new Matrice(tab.length,1);
		
		for(int i = 0;i<tab.length; i++){
			
			res.coeffs[i][0]=tab[i];
			
		}
		
		return res;
		
	}
	
	 public static void testcreeVecteur(){
		 
		 double[] tab = {5,8,9,4,7};
		 
		 System.out.println( creeVecteur(tab));
	
}

public int getNbLigne(){
	return this.nbrLig;
}
public double getCoeff(int nLigne , int nColone){
	if(nLigne>this.nbrLig && nColone>this.nbrCol){
	throw new Error ("Error getCoeffs : le get demandé est en dehors de la matrice");
}
return this.coeffs[nLigne][nColone];
	
}
public void setCoeff(int nLigne , int nColone, double x){
	if(nLigne>this.nbrLig && nColone>this.nbrCol){
throw new Error ("Error set Coeffs : le set demandé est en dehors de la matrice");
}

	this.coeffs[nLigne][nColone] = x;
}
public static  void testSetCoeff(){
	Matrice m = matAleatoirUnOuDeux(3,3,0.5);
	m.setCoeff(1, 3, 5);
	System.out.println( m );
}
	/*renvoi une Matrice resultante de la fusion des lignes de deux Matrices
     * */
	public Matrice concatLig(Matrice M1){
		
		int col1 = M1.nbrCol;
			int col2 = this.nbrCol;
		Matrice res = new Matrice(M1.nbrLig+this.nbrLig,M1.nbrCol); //creer la matrice de bonne dimention
		
		if(M1.nbrCol!=this.nbrCol){
			throw new Error("error concatene : dimentions incompatibles");
		}
		for(int i = 0 ; i< this.nbrCol*this.nbrLig ; i++){
			
			res.coeffs[i/ col2][i%col2]=this.coeffs[i/col2][i%col2];
			
		}
		for(int i = 0 ; i< M1.nbrCol*M1.nbrLig ; i++){
			
			res.coeffs[(i/ col1)+this.nbrLig][i% col1]=M1.coeffs[i/col1][i%col1]; // commence la copie à partir de la ligne du tableau this
			
		}
		return res;
	}

public static void testConcatLig(){
	
	Matrice m1 = matAleatoirUnOuDeux(3,3,0.5);
	Matrice m2 = matAleatoirUnOuDeux(1,3,0.5);
	
	System.out.println(m1.concatLig(m2));
}
/*meme chose pour les colone
     * */
public  Matrice concatCo(Matrice M1){
	
	int col1 = M1.nbrCol;
			int col2 = this.nbrCol;
	Matrice res = new Matrice(M1.nbrLig,M1.nbrCol+this.nbrCol);
		
		if(M1.nbrLig != this.nbrLig){
		throw new Error("error concatCo : dimentions incompatibles "); 
	}
		for(int i = 0 ; i< this.nbrCol*this.nbrLig ; i++){
			
			res.coeffs[i/  col2][i%  col2]=this.coeffs[i/ col2][i%  col2]; 
			
		}
		for(int i = 0 ; i< M1.nbrCol*M1.nbrLig ; i++){
			
			res.coeffs[i/  col1][(i%  col1 )+  col2]=M1.coeffs[i/  col1][i%  col1];
			
		}
			return res;
		
	}
	public static void testConcatCo(){
	
	Matrice m1 = matAleatoirUnOuDeux(3,3,0.5);
	Matrice m2 = matAleatoirUnOuDeux(3,2,0.5);
	
	System.out.println(m1.concatCo(m2));
}
/*renvoi une Matrice de largeur reduite 
     * */
public Matrice subLignes(int Nmin, int Nmax){
	
	int col = this.nbrCol;
	
	if(0>Nmin || Nmin>Nmax){      //test coherance valeurs
		throw new Error("error subLignes :  dimentions incorrectes"); 
	}
		Matrice res = new Matrice(Nmax-Nmin, this.nbrCol);   
		for(int i =0 ; i<(Nmax-Nmin)*this.nbrCol ; i++){     
			
			res.coeffs[i/col][i%col] = this.coeffs[(i/col) +Nmin][i%col]; //commence la copie à min fini à max
			}	
		return res;
}
	
	public static void testSubLignes(){
		
		Matrice m1 = matAleatoirUnOuDeux(4,3,0.5);
		System.out.println(m1);
		System.out.println(m1.subLignes(1,3));
		
	} /*meme chose pour les colone
     * */
	public Matrice subCols(int Nmin, int Nmax){
		
		int lig = this.nbrLig;
	if(0>Nmin || Nmin>Nmax){
		throw new Error("error subCols");
	}
		Matrice res = new Matrice(this.nbrLig,Nmax-Nmin);
		for(int i =0 ; i<(Nmax-Nmin)*res.nbrLig ; i++){
			
			res.coeffs[i%lig][ i/lig] = this.coeffs[i%lig ][i/lig+Nmin]; 
			}
		return res;
}
	public static void testSubCols(){
		
		Matrice m1 = matAleatoirUnOuDeux(4,4,0.5);
		System.out.println(m1);
		System.out.println(m1.subCols(0,2));
		
	} 
	public Matrice copie(){ 
		Matrice res = this ;
		return res;
	}
		public static void testCopie(){
			 System.out.println(matAleatoirUnOuDeux(4,4,0.5).copie());
		}
		/*calcule la transposée
     * */
	public Matrice transposee(){
		
		int col = this.nbrLig;
		Matrice res = new Matrice(this.nbrCol,this.nbrLig); //  dimention son inversé
		
		for(int i = 0 ; i< this.nbrCol*this.nbrLig;i++){
			
			res.coeffs[i/col][i%col] =  this.coeffs[i%col][i/col]; // col =  res.lig
			
		}
		return res;
	}
	
	public static void testTransposee(){
		Matrice m = matAleatoirUnOuDeux(2,5,0.5);
		System.out.println(m);
		System.out.println(m.transposee());
	}
	/*renvoi la matrice mise au carré
     * */
	public  Matrice metAuCarre(){
		
		Matrice res = new Matrice(this.nbrCol + this.nbrLig,this.nbrCol + this.nbrLig); // met la dimmension au carré
		
	   Matrice temp1 = this.concatCo(identite(this.nbrLig)); //concatene avec identite
	      Matrice temp2 = identite(this.nbrCol).concatCo(this.transposee());// identité concatene avec tansposée
	      
	      res = temp1.concatLig(temp2); //concatene les resultats intermedaires 
	                
		return res;
		
	}
	
	public static void testMetAucarre(){
		
		Matrice m = matAleatoirUnOuDeux(1,5,0.5);
		System.out.println(m.metAuCarre());
	}
		public static int intAlea(int bmin, int bmax){
			
			return (int)(Math.random()*(bmax-bmin)+bmin);
		}
		
		public static void test2(){
			
			int nc = intAlea(1,4);
			int nl = intAlea(1,4);
			
			Matrice m = matAleatoirUnOuDeux(nl,nc,0.33);
			 System.out.println(m);
			
			m = m.metAuCarre();
			  System.out.println(m);
			
			System.out.println(m.subLignes(0,nl).subCols(0,nc));
			 
		}/*renvoi la somme de deux matrice 
     * */
		public Matrice add(Matrice N){
		
		int col = this.nbrCol;
		Matrice res = new Matrice(this.nbrLig,this.nbrCol);
		
		if(this.nbrCol!=N.nbrCol || this.nbrLig!=N.nbrLig) {
			throw new Error("error add : dimentions incompatibles");
		}
			for(int i = 0;i<this.nbrCol*this.nbrLig;i++){
				
			res.coeffs[i/col ][i%col ]	= this.coeffs[i/ col ][i%col ]+N.coeffs[i/ col][i%col ]; //somme
			}
		return res;
	}
		
		public static void testAdd(){
			
			Matrice a = matAleatoirUnOuDeux(2,5,0.5);
			Matrice b = matAleatoirUnOuDeux(2,5,0.5);
			
			System.out.println(a);
			System.out.println(b);
			System.out.println(a.add(b));
		}
		/*renvoi l'opposé d'une matrice
     * */
		public Matrice opp(){
			int col = this.nbrCol;
			Matrice res = new Matrice(this.nbrLig,this.nbrCol);
			for(int i = 0;i<this.nbrCol*this.nbrLig;i++){
			res.coeffs[i/col][i%col]	=  -this.coeffs[i/ col][i% col];
		}
		return res;
	}
	public static void testOpp(){
			
			Matrice a = matAleatoirUnOuDeux(2,5,0.5);		
			System.out.println(a);
			System.out.println(a.opp());
		}	/*renvoi la difference de deux matrices
     * */
	public Matrice moins(Matrice N){
		
		Matrice res = this.add(N.opp());
		return res;
		
	}
	public static void testMoins(){
			
			Matrice a = matAleatoirUnOuDeux(2,5,0.5);
			Matrice b = matAleatoirUnOuDeux(2,5,0.5);
			
			System.out.println(a);
			System.out.println(b);
			
    System.out.println(a.moins(b));
		}
		/*renvoi le  produit deux matrices
     * */
	public Matrice mult(Matrice N){
		
		Matrice res = new Matrice(this.nbrLig,N.nbrCol);
		int col = res.nbrCol;
		
		
		if(this.nbrCol != N.nbrLig){
				throw new Error (" error mult : dimentions incompatibles");
			}
			for(int i = 0;i<this.nbrLig*N.nbrCol;i++){
				for(int j = 0;j< this.nbrCol; j++){
				
				res.coeffs[i/ col][i%col]= res.coeffs[i/ col][i% col]+   this.coeffs[i/col][j]*N.coeffs[j][i%col]; 
			}
		}
	return res ;
	}
	public static void testMult(){
			
			Matrice a = matAleatoirUnOuDeux(2,3,0.3);
			Matrice b = matAleatoirUnOuDeux(3,3,0.3);
			
			System.out.println(a);
			System.out.println(b);
			
    System.out.println(a.mult(b));
		}
		/* si la ligne i1 et i2 sont egale renvoi un sinon -1 et fait la permutation de i1 et i2
     * */
		public int permuteLigne(int i1,int i2){
			
			double temp;
			int i = 0;
		
while(this.coeffs[i1][i]==this.coeffs[i2][i]){
	i++;
	if(i==this.nbrCol){
		return 1;
	}
}
for(int j = 0 ; j<this.nbrCol;j++){
	
	temp = this.coeffs[i1][j];
	this.coeffs[i1][j]=this.coeffs[i2][j];
	this.coeffs[i2][j]=temp;
}
			return -1;
		}
		public static void testLigne(){
			
			Matrice m = matAleatoirUnOuDeux(3,3,0.5);
			System.out.println(m);
			System.out.println(m.permuteLigne(1,1));
			m.permuteLigne(1,2);
			System.out.println(m);
			System.out.println(m.permuteLigne(1,2));
		}
		/*rend nul le coeff [i2] [i1] avec le pivot [i1][i1]
     * */
		public void transvection(int i1,int i2){
			
			double p;  
			
			if(i1< this.nbrCol){
				if(this.coeffs[i1][i1]==( 0.0) ){
				
					throw new Error("error  transvection : pivot nul");
				}
			
					p = this.coeffs[i2][i1]/this.coeffs[i1][i1];
				
				for(int i = 0; i<this.nbrCol;i++){
				
						this.coeffs[i2][i] =	this.coeffs[i2][i]  -	p*this.coeffs[i1][i];
					}
					this.coeffs[i2][i1]=0.0; // on fixe à 0.0 pour evité les erreurs liées à l'approximation des float
				}
			}
		public static void testTransvection(){
			
			Matrice m = matAleatoirUnOuDeux(3,3,0.2);
			System.out.println(m);
			m.transvection(1,2);
			System.out.println(m);
		}
		/*renvoi la ligne du plus grand pivot pour une colone donnée
     * */
		public int lignePlusGrandPivot(int e ){
			
			double EPSILON_PIVOT = 0.00000001;    // constante liée à à l'approximation des float
			int imax = e;                                                      // par default le pivot et sur la diagonale
			
			for(int i =e ; i<this.nbrLig ; i++){
			
				if(Math.abs(this.coeffs[i][e]) > Math.abs(this.coeffs[imax][e])){   //test toute les lignes sous la diagonale
				
					imax = i;
				}
			}
			if(Math.abs(this.coeffs[imax][e])>  EPSILON_PIVOT){; //si le pivot trouvé et non nul 
			return imax;
		}
			return -1;
		}
		public static void testLignePlusGrandPivot(){
			
			Matrice m = matAleatoirUnOuDeux(3,3,0.5);
			System.out.println(m);
			System.out.println(m.lignePlusGrandPivot(0));
		}
		/*rend la matrice triangulaire supp
     * */
	 public  ResGauss descenteGauss(){
		 
		 
		 ResGauss res = new ResGauss();
                 
		 int imax;
		for(int e = 0 ; e< this.nbrLig-1;e++){
		
		imax =  this.lignePlusGrandPivot(e);   //identifie le plus grand pivot la la ligne
		
		 if(imax != e && imax != -1){                                                        // si le pivot nest pas sur la diagonale on effectue une permutation
			 this.permuteLigne(imax,e);                   
			 res.signature++;                                     // incremente la signature
		 }
	
	for(int i =e+1 ;i<this.nbrLig;i++){  
		
		if(this.coeffs[e][e] != 0.0){       
		if(this.coeffs[i][e] != 0.0){                //si le coeff de la ligne traité est non nul on l'annule avec tranvesction
			this.transvection(e,i);
			res.rang++;                                         //incrementation du rang
		}

	}
	}
}

		 return res;
	 }
	 public  static void test4(){
		 
		 
		 Matrice mattest = matTest1();
		 Matrice vecteur = new Matrice(3,1);
		vecteur.coeffs[0][0] = 1;
		vecteur.coeffs[1][0] = 2;
		 vecteur.coeffs[2][0] = 3;
		 System.out.println( mattest);
		System.out.println( mattest.concatCo(vecteur));
		mattest = mattest.concatCo(vecteur);
		ResGauss op =   mattest.descenteGauss();
		 System.out.println(  mattest);
		  System.out.println( op.rang+"   "+op.signature);
	 }
	 public double determinant(){
		 
		 double res = 1;
		 ResGauss memoire ;
		
		memoire =   this.descenteGauss();
			
	
		for(int i = 0; i<this.nbrLig;i++){ 
		 res = res * this.coeffs[i][i];  //produit des coeffs diagonaux
	 }
               
       res = res*memoire.signature;
	   return res;
                
	 }
	 public static void testDeterminant(){
		 
		 Matrice gg =  matTest1();
	gg.coeffs[1][1]=-4;	gg.coeffs[2][2]=-8;
		 System.out.println( gg.determinant() );
		  System.out.println( gg );
 gg =  matTest1();
 System.out.println( gg.determinant() );
  System.out.println( gg );
	 }/*
         
        transforme une Matrice en matrix jama
         */
          public Matrix toMatrix(){

              int nl =this.nbrLig;
int nc = this.nbrLig;

Matrix res= new Matrix(nl,nc);    

for(int i = 0; i < nl;i++){
for(int j = 0; j < nc;j++){

res.set(i,j,this.coeffs[i][j]);

}
}
return res;
}/*
          fait l'operation inverse
          */
          public static Matrice fromMatrix(Matrix M){
         
              int nl =M.getRowDimension();
              int nc = M.getColumnDimension() ;
              Matrice res = new Matrice(nl,nc );
   
      for(int i = 0; i < nl;i++){
      for(int j = 0; j < nc;j++){        
              
          res.coeffs[i][j]= M.get(i, j);
      }
      }
              return res ;
}
          public double testDet(){
              
              double un = this.toMatrix().det();
              double det = this.determinant()- un ;
              det = Math.abs(det);
              System.out.println(this);
              System.out.println(un+"   "+this.determinant());
              System.out.println("");
              
              return det;
          }
          public static void test5(){
              
              double testDet = 0;
               double testTempDet;
               Matrice M ;
               int iter = (int)(Math.random()*250);
               int dimention = 3;
               
               for (int i = 0; i < iter ; i++) {
                   
                   M = matAleatoirUnOuDeux(dimention,dimention,1/3);
                   
                   System.out.println(M);
                    System.out.println("");
                   testTempDet =  M.testDet();
                   
                   if(testDet < testTempDet){
                       
                   testDet = testTempDet;
                   
                   
                   }  
               }
               System.out.println(testDet);
               
           }
	 /*
             renvoi la matrice diagonalisée à partire d'une matrice triangulaire supp
*/
	 public void remonteeGauss(){
		 
		 for (int i = this.nbrLig -1;i>0 ; i--){
			 for(int k = i-1 ; k>=0 ; k--){
				 
			 this.transvection(i,k);
		 }
		 }
		 }
	 
	   public static void testRemonteeGauss(){
		   
		    Matrice gg =  matTest1();
	gg.coeffs[1][1]=-4;	gg.coeffs[2][2]=-8;
	double[] vecteur = {1,2,3};
	Matrice Vecteur = creeVecteur(vecteur);
	gg = gg.concatCo(Vecteur);
	
		 System.out.println( gg.determinant() );
		  System.out.println( gg );
         
		  gg.remonteeGauss();
		   System.out.println( gg);
		   
	   }
           
           
	 
    public static void main(String[] args){
     
      // testToString();
   //   testIdentite();
     // testAleatoirUnOuDeux();
     //   testcreeVecteur();
 //   testSetCoeff();
    //   testConcatLig();
   //    testConcatCo();
  //    testSubLignes();
    //  testSubCols();
 //     testCopie();
  //testTransposee();
 //     testMetAucarre();
  //  test2();
//    testAdd();
//testOpp();
//testMoins();
//testMult();
//testLigne();
 //testTransvection();
// testLignePlusGrandPivot();
//test4();
 //testDeterminant();
//testRemonteeGauss();
//test5();
System.out.println(Math.sqrt(5));
    }  
}