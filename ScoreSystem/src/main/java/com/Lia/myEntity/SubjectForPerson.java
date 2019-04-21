package com.Lia.myEntity;

public class SubjectForPerson {
	
	@Override
	public String toString() {
		return "[姓名: "+name+" 科目:"+subject+" 分数:  "+score+" 排名: "+NO+"]";
	}
	
	private String subject;
	private String name;
	private String id;
	private int score;
	private int NO;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNO() {
		return NO;
	}

	public void setNO(int nO) {
		NO = nO;
	}


   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   private String percent;

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public int getRank() {
      return NO;
   }

   public void setRank(int NO) {
      this.NO = NO;
   }

   public String getPercent() {
      return percent;
   }

   public void setPercent(String percent) {
      this.percent = percent;
   }
}
