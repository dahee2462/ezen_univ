package ateam.db;

public class TestDb {

	
	public void test()
	{
		DBManager db = new DBManager();
		
		if(db.connect())
		{
			String sql = "";
			
			int count = db.prepare(sql)
						  .setInt(0)
						  .update();
			
			if(count >0 )
			{
				
			}
			
			if(db.prepare("SELECT").setString("nname").read())
			{
				if(db.getNext())
				{
					int nno = db.getInt("nno");
				}
			}
			
			
			
			db.disconnect();
		}
	}
}




