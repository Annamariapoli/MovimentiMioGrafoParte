package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.MovNum;
import bean.Movimento;

public class Dao {
	
	public List<Integer> getCircoscrizioni(){                //ok
		Connection conn =DBConnect.getConnection();
		String query ="select m.CIRCOSCRIZIONE_PROVENIENZA from movimenti_intraurbani m union "
				+ "(select m.CIRCOSCRIZIONE_DESTINAZIONE  from  movimenti_intraurbani m )";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			List<Integer> circo = new LinkedList<Integer>();
			ResultSet res = st.executeQuery();
			while(res.next()){
				circo.add(res.getInt("CIRCOSCRIZIONE_PROVENIENZA"));
				//.add(res.getInt("CIRCOSCRIZIONE_DESTINAZIONE"));
				
			}
			conn.close();
			System.out.println(circo.toString());
			return circo;
			
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	public List<String> getNumCambiEDestinazione(int circoprovenienza){                //ok
		String query="select  m.CIRCOSCRIZIONE_DESTINAZIONE, count(*) as num "
				+ "from movimenti_intraurbani m "
				+ "where m.TIPO_EVENTO='Cambio indirizzo' and m.CIRCOSCRIZIONE_PROVENIENZA=?  "
				+ "group by m.CIRCOSCRIZIONE_DESTINAZIONE "
				+ "order by num DESC";
	
		Connection conn =DBConnect.getConnection();
		List<String> all=new LinkedList<>();
		try{
			PreparedStatement st= conn.prepareStatement(query);
			st.setInt(1,circoprovenienza );
			ResultSet res = st.executeQuery();
			while(res.next()){
				all.add("Destinazione:   " +res.getInt("CIRCOSCRIZIONE_DESTINAZIONE")+"  --  Num Di Cambi: " +res.getInt("num")+" \n");
			}
			conn.close();
			return all;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}
	
	
	public int CalcolaPesoCircoscrizioni(int c1, int c2){       //ok
		String query ="select count(*) as num  "
				+ "from movimenti_intraurbani m  "
				+ "where m.CIRCOSCRIZIONE_PROVENIENZA=? and m.CIRCOSCRIZIONE_DESTINAZIONE=?";
		Connection conn =DBConnect.getConnection();
		int peso=-1;
		try{
		      PreparedStatement st = conn.prepareStatement(query);	
		      st.setInt(1, c1);
		      st.setInt(2, c2);
		      ResultSet res = st.executeQuery();
		      if(res.next()){
		    	  peso = res.getInt("num");
		      }
		      conn.close();
		     // System.out.println(peso);
		      return peso;
		}catch(SQLException e ){
			e.printStackTrace();
			//System.out.println(-1);
			return -1;
		}
		
	}
	
	
	public static void main(String [] args){
		Dao dao = new Dao();
		//dao.CalcolaPesoCircoscrizioni(3, 4);
	}
}
