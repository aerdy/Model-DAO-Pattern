public class PersonDaoJDBC{
	private Connection conn;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	private PreparedStatement getByIdStatement;
	private PreparedStatement getAllStatement;


	private final String insertQuery = "insert into person (name,password) values (?,?)";
	private final String deleteQuery = "delete from person where id=?";
	private final String getByidQuery = "select * from person where id = ?";
	private final String getAllQuery = "select * from person";
	private final String updateQuery = "update person set name=?,password=? where id=?";
	
	//method connection
	public void setConnection (Connection conn) throws SQLException {
	this.conn = conn;
	insertStatement = this.conn.preparedStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
	
	updateStatement = this.conn.prepareStatement(updateQuery);
	deleteStatement = this.conn.prepareStatement(deleteStatement);
	getByIdStatement = this.conn.prepareStatement(getByidQuery);
	getAllStatement = this.conn.prepareStatement(getAllQuery);
	}


	public Person save (Person person) throws SQLException{
	if(person.getId() == null){
	insertStatement.setString(1, person.getName());
	insertStatement.setString(2, person.getPassword());
	int id = insertStatement.executeUpdate();
	person.setId(id);
	}else{
	updateStatement.setString(1, person.getName());
	updateStatement.setString(2, person.getPassword());
	updateStatement.setInt(3, person.getId());
	updateStatement.executeUpdate();
	}
	return person;
	}

	public Person delete(Person person)throws SQLException{
	deleteStatement.setInt(1, person.getId.getId());
	deleteStatement.executeUpdate();
	return person;
	}
	
	public Person getByid(Long id)throws SQLException{
	getByIdStatement.setLong(1, id);	
	ResultSet rs = getByIdStatement.executeQuery();

	if(rs.next()){
	PersonModel person = new PersonMoldel();
	person.setId(rs.getLong("id"));
	person.setName(rs.getString("name"));
	person.setPassword(rs.getString("password"));
	return person;
	}
	return null;
	}

	public List<Person> getAll() throws SQLException{
	List<Person> persons = new ArrayList<Person>();
	ResulSet rs = getAllStatement.executeQuery();
	while(rs.next()){
	PersonModel person = new PersonModel();
	person.setId(rs.getLong("id"));
	person.setName(rs.getString("name"));
	person.setPassword(rs.getString("password"));
	persons.add(person);

	}
	return persons;
	}
	
}

