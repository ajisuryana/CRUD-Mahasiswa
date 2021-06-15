<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
 $response = array();
 //mendapatkan data
 $nim = $_POST['nim'];;
 $nama = $_POST['nama'];;
 $tlp = $_POST['tlp'];;
 $prodi = $_POST['prodi'];;
 require_once('config.php');
 $sql = "SELECT * FROM tbl_mahasiswa WHERE nim ='$nim'"; //Cek nim sudah terdaftar apa belum
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 if(isset($check)){
 $response["value"] = 0;
 $response["message"] = "NIM telah terdaftar!";
 echo json_encode($response);
 } else {
 $sql = "INSERT INTO tbl_mahasiswa (nim,nama,tlp,prodi)
VALUES('$nim','$nama','$tlp','$prodi')";
 if(mysqli_query($con,$sql)) {
 $response["value"] = 1;
 $response["message"] = "Data Berhasil Disimpan";
 echo json_encode($response);
 } else {
 $response["value"] = 0;
 $response["message"] = "oops! Coba lagi!";
 echo json_encode($response);
 }
 }
mysqli_close($con); // tutup database
} else {
 $response["value"] = 0;
$response["message"] = "oops! Coba lagi!";
 echo json_encode($response);
}
?>