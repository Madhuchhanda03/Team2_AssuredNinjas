package pojo;

import java.util.List;

public class UserPojo  {
	    public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserFirstName() {
			return userFirstName;
		}

		public void setUserFirstName(String userFirstName) {
			this.userFirstName = userFirstName;
		}

		public String getUserLastName() {
			return userLastName;
		}

		public void setUserLastName(String userLastName) {
			this.userLastName = userLastName;
		}

		public String getUserMiddleName() {
			return userMiddleName;
		}

		public void setUserMiddleName(String userMiddleName) {
			this.userMiddleName = userMiddleName;
		}

		public String getUserPhoneNumber() {
			return userPhoneNumber;
		}

		public void setUserPhoneNumber(String userPhoneNumber) {
			this.userPhoneNumber = userPhoneNumber;
		}

		public String getUserLocation() {
			return userLocation;
		}

		public void setUserLocation(String userLocation) {
			this.userLocation = userLocation;
		}

		public String getUserTimeZone() {
			return userTimeZone;
		}

		public void setUserTimeZone(String userTimeZone) {
			this.userTimeZone = userTimeZone;
		}

		public String getUserLinkedinUrl() {
			return userLinkedinUrl;
		}

		public void setUserLinkedinUrl(String userLinkedinUrl) {
			this.userLinkedinUrl = userLinkedinUrl;
		}

		public String getUserEduUg() {
			return userEduUg;
		}

		public void setUserEduUg(String userEduUg) {
			this.userEduUg = userEduUg;
		}

		public String getUserEduPg() {
			return userEduPg;
		}

		public void setUserEduPg(String userEduPg) {
			this.userEduPg = userEduPg;
		}

		public String getUserComments() {
			return userComments;
		}

		public void setUserComments(String userComments) {
			this.userComments = userComments;
		}

		public String getUserVisaStatus() {
			return userVisaStatus;
		}

		public void setUserVisaStatus(String userVisaStatus) {
			this.userVisaStatus = userVisaStatus;
		}

		public List<Role> getUserRoleMaps() {
			return userRoleMaps;
		}

		public void setUserRoleMaps(List<Role> userRoleMaps) {
			this.userRoleMaps = userRoleMaps;
		}

		public UserLogin getUserLogin() {
			return userLogin;
		}

		public void setUserLogin(UserLogin userLogin) {
			this.userLogin = userLogin;
		}

		private String userId;
	    private String userFirstName;
	    private String userLastName;
	    private String userMiddleName;
	    private String userPhoneNumber;
	    private String userLocation;
	    private String userTimeZone;
	    private String userLinkedinUrl;
	    private String userEduUg;
	    private String userEduPg;
	    private String userComments;
	    private String userVisaStatus;
	    private List<Role> userRoleMaps;
	    private UserLogin userLogin;

	    // Getters and Setters
	    
	    public static class Role {
	        public String getRoleId() {
				return roleId;
			}
			public void setRoleId(String roleId) {
				this.roleId = roleId;
			}
			public String getUserRoleStatus() {
				return userRoleStatus;
			}
			public void setUserRoleStatus(String userRoleStatus) {
				this.userRoleStatus = userRoleStatus;
			}
			private String roleId;
	        private String userRoleStatus;
	        public Role(String roleId,String userRoleStatus) {
	        	this.roleId = roleId;
	        	this.userRoleStatus = userRoleStatus;
	        }

	        // Getters and Setters
	    }

	    public static class UserLogin {
	        public String getUserLoginEmail() {
				return userLoginEmail;
			}
			public void setUserLoginEmail(String userLoginEmail) {
				this.userLoginEmail = userLoginEmail;
			}
			public String getLoginStatus() {
				return loginStatus;
			}
			public void setLoginStatus(String loginStatus) {
				this.loginStatus = loginStatus;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			private String userLoginEmail;
	        private String loginStatus;
	        private String status;
	        public UserLogin(String userLoginEmail,String loginStatus,String status){
	        	this.userLoginEmail = userLoginEmail;
	        	this.loginStatus = loginStatus;
	        	this.status = status;
	        }
	        // Getters and Setters
	    }
}
