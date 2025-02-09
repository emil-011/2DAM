package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
// [START firestore_deps]
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query;
// [END firestore_deps]
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class PruebaFirebase {
	public Firestore db;

	public PruebaFirebase(String projectId) throws Exception {
		InputStream serviceAccount = getClass().getClassLoader()
				.getResourceAsStream("aacc-prueba-firebase-adminsdk-fbsvc-4b66fbed03.json");
		// GoogleCredentials.getApplicationDefault();
		// NoCredentials.getInstance();

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId(projectId)
				.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
		Firestore db = firestoreOptions.getService();

		this.db = db;

	}

	/**
	 * Initialize Firestore using default project ID.
	 */
	public PruebaFirebase() {
		Firestore db = FirestoreOptions.getDefaultInstance().getService();
		this.db = db;
	}

	Firestore getDb() {
		return db;
	}

	public static void main(String[] args) throws Exception {
		// default project is will be used if project-id argument is not available
		String projectId = "aacc-prueba";
		PruebaFirebase quickStart = (projectId != null) ? new PruebaFirebase(projectId) : new PruebaFirebase();
		// quickStart.run();
		// FirebaseApp.initializeApp();
		quickStart.lectura();
	}

	/**
	 * Prueba de lectura de datos en Firebase
	 */
	void lectura() throws Exception {
		// LECTURA con filtro
		// asynchronously retrieve filtered users
		CollectionReference usuarios = db.collection("Usuarios");
		Query query1 = usuarios.whereEqualTo("nombre", "Emilio");
		ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();

		for (DocumentSnapshot document : querySnapshot1.get().getDocuments()) {
			System.out.println("El Id del registro es: " + document.getId() + "\n");
		}

		// A�adir a las Reglas de Clooud Firestore (En la pagina donde se ven los datos
		// de la BBDD, pesta�a Reglas)
		// allow read, write: if request.auth.uid != null;

		// Otra opci�n para dar acceso hasta 18 mayo 2025
		// allow read, write: if request.time < timestamp.date(2025, 5, 18);

		// LECTURA sin filtro
		// asynchronously retrieve all users
		System.out.println("Listar todos los usuarios");
		ApiFuture<QuerySnapshot> query = db.collection("Usuarios").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = null;
		try {
			querySnapshot = query.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			Map<String, Object> datos = document.getData();
			datos.isEmpty();
			System.out.println("User: " + document.getId());
			System.out.println("nombre: " + document.getString("nombre"));
			System.out.println("password: " + document.getString("password"));
			System.out.println("rol: " + document.getString("rol"));
		}

		// AGREGAR DATOS
		DocumentReference docRef = db.collection("Usuarios").document("2damprueba");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("nombre", "Emilio");
		data.put("password", "1234");
		data.put("rol", "Usuario");

		ApiFuture<WriteResult> result = docRef.set(data);

		try {
			System.out.println("Update time : " + result.get().getUpdateTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		db.close();
	}

}
