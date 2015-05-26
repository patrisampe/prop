package utiles;

import java.util.ArrayList;



public class ImportLog {
	public class Entry {
		private String type;
		private String foreignKey;
		private String foreingKey2;
		private String warning;
		private CodiError error;
		private Integer state;
		private Integer object;
		
		private Entry() {
			state = 0;
		}
		public boolean isSuccesful() {
			return state < 4 && state >= 2;
		}
		public boolean hasWarning() {
			return state == 4;
		}
		public boolean hasError() {
			return state == 5;
		}
		public String getType() {
			return type;
		}
		public boolean setType(String type) {
			if (state > 0) return false;
			this.type = type;
			state = 1;
			return true;
		}
		public String getForeignKey() {
			return foreignKey;
		}
		public boolean setForeignKey(String foreignKey) {
			if (state != 1) return false;
			this.foreignKey = foreignKey;
			state = 2;
			return true;
		}
		public String getForeingKey2() {
			return foreingKey2;
		}
		public boolean setForeingKey2(String foreingKey2) {
			if (state != 2) return false;
			this.foreingKey2 = foreingKey2;
			state = 3;
			return true;
		}
		public String getWarning() {
			if (state != 4) return null;
			return warning;
		}
		public boolean setWarning(String warning) {
			if (state > 4) return false;
			this.warning = warning;
			state = 4;
			return true;
		}
		public CodiError getError() {
			if (state != 6) return null;
			return error;
		}
		public boolean setError(CodiError error) {
			this.error = error;
			state = 6;
			return true;
		}
		public Integer getState() {
			return state;
		}
		public Integer getObject() {
			if (state != 5) return null;
			return object;
		}
		public boolean setObject(Integer object) {
			if (state != 4) return false;
			this.object = object;
			state = 5;
			return true;
		}
		
	}
	
	private ArrayList<Entry> log;
	private Entry ongoing;
	
	public ImportLog() {
		log = new ArrayList<Entry>();
		ongoing = new Entry();
	}
	
	public Integer size() {
		return log.size();
	}
	
	public void add(String s) {
		if (ongoing.setType(s));
		else if(ongoing.setForeignKey(s));
		else if(ongoing.setForeingKey2(s));
		else {
			saveEntry();
			add(s);
		}
	}
	
	public void ok() {
		saveEntry();
	}
	
	public void addW(String s) {
		ongoing.setWarning(s);
	}
	
	public void addWObject(Integer i) {
		ongoing.setObject(i);
	}
	
	public void addError(CodiError c) {
		ongoing.setError(c);
	}

	private void saveEntry() {
		if(ongoing.getState() > 0) log.add(ongoing);
		ongoing = new Entry();
	}
	
	public Entry getEntry(Integer i) {
		return log.get(i);
	}
	
	public String getType(Integer i) {
		return log.get(i).getType();
	}
	
	public String getForeignKey(Integer i) {
		return log.get(i).getForeignKey();
	}

	public String getForeingKey2(Integer i) {
		return log.get(i).getForeignKey();
	}
	
	public String getMessage(Integer i) {
		Entry e = log.get(i);
		if (e.isSuccesful()) return "OK";
		if (e.hasWarning()) return e.getWarning();
		if (e.hasError()) return e.getError().toString();
		return "Entrada mal construida.";
	}
	
	public boolean hasError(Integer i) {
		return log.get(i).hasError();
	}
	
	public boolean isSuccesful(Integer i) {
		return log.get(i).isSuccesful();
	}
	
	public boolean hasWarning(Integer i) {
		return log.get(i).hasWarning();
	}

	public void clear() {
		saveEntry();
		log = new ArrayList<Entry>();
	}
}
