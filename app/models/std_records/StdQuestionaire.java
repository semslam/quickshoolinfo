package models.std_records;

public class StdQuestionaire {
	
	private String[] watDoLikeAbUrSch; // what do did like about ur school
	private String WhoAreIsFrTeach; // who is your favourite teachers
	private String[] watAreUrFrSub; // what are your favourite subject
	private FavouriteTeacher favouriteTeacher;

	class FavouriteTeacher{
		public String subTeach;// what subject does your teacher teach
		private String[] reasonChoosing;// what are your reason for choosing this teacher
	}
}
