# QUADTREE
## Definisi
*Quadtree* adalah sebuah struktur data pohon yang akarnya memiliki persis empat buah simpul anak. *Quadtree* digunakan untuk merepresentasikan informasi spasial yang terdapat pada sebuah daerah tertentu. Biasanya struktur data ini digunakan untuk menyimpan informasi sebuah data dua dimensi, contohnya seperti pada informasi pewarnaan pada sebuah gambar.

<p align="center">
  <img width="460" height="300" src="http://codeforces.com/predownloaded/7b/ff/7bff9daa7c6aec3f2ff2717b7ee778e9c0ba43f3.png">
 <br/>
 Contoh <i>Quadtree<i>
</p>

*Quadtree* komplit yaitu pohon yang setiap simpulnya memiliki empat buah anak(kecuali simpul daun) dan semua simpul daunnya berada pada level yang sama. *Quadtree* ini disebut pohon piramid atau *Tree-Pyramid*.

<p align="center">
  <img width="460" height="300" src="http://user.engineering.uiowa.edu/~dip/lecture/DataStructures/f3.5.gif">
</p>

## Jenis-Jenis *Quadtree*
- *Region Quadtree* : *Quadtree* yang merepresentasikan partisi dari sebuah daerah dua dimensi menjadi empat subkuadran dengan simpul daun menyimpan informasi dari sebuah subkuadran tertentu. Simpul dalam pohon tersebut mempunyai empat buah simpul anak atau tidak sama sekali(simpul daun). Dalam merepresentasikan sebuah gambar, Region Quadtree dapat digunakan, yaitu dengan membagi daerah setiap gambar secara rekursif sampai pada level tertentu(biasanya sampai satu *pixel*) dan menyimpan informasi warna setiap *pixel*nya. Region Quadtree juga dapat digunakan untuk merepresentasikan nilai variabel pada sebuah kumpulan data, contohnya penyimpanan temperatur rata-rata pada sebuah daerah.
<p align="center">
  <img width="460" height="300" src="https://upload.wikimedia.org/wikipedia/commons/d/d7/Quadtree_compression_of_an_image.gif">
 <br/>
 <i>Region Quadtree<i> untuk penyimpanan gambar
</p>

- *Point Quadtree* : *Quadtree* untuk merepresentasikan data dua dimensi, contohnya kumpulan titik-titik yang ingin disimpan. *Quadtree* ini sangat efisien apabila digunakan untuk membandingkan data yang terurut, biasanya kompleksitas waktunya adalah *O(log n)*.

- *Point-Region Quadtree* : *Quadtree* yang sama seperti *Region Quadtree*, hanya saja informasi yang disimpan pada setiap simpul adalah kumpulan titik-titik yang berada pada daerah tertentu(yang direpresentasikan oleh simpul tersebut).

- *Edge Quadtree* : *Quadtree* untuk merepresentasikan kumpulan garis. Garis yang mempunyai kelengkungan dibagi menjadi beberapa garis hingga menjadi garis lurus atau mencapai level tertentu. Cara ini dapat menciptakan pohon yang sangat tidak seimbang sehingga mempersulit pencarian simpul tertentu.

- *Compressed Quadtree* : *Quadtree* ini adalah sebuah pohon yang direduksi agar tidak menyimpan informasi yang tidak dibutuhkan. Contohnya seperti penggunaan *Region Quadtree* untuk merepresentasikan gambar. Dengan menggunakan *Compressed Quadtree*, sebuah daerah pada gambar yang memiliki warna yang sama dapat direpresentasikan oleh sebuah simpul, tanpa harus membagi simpul yang merepresentasikan daerah tersebut hingga level satuan *pixel*. Penyimpanan data menjadi lebih efisien dan pencarian warna pada *pixel* tertentu menjadi lebih mudah.

## Implementasi
```java
/*
	Berikut adalah implementasi sederhana dari struktur data Region Quadtree
    menggunakan bahasa Java
*/

// Kelas Point sebagai penyimpan informasi kordinat dua dimensi
class Point{
	// Absis
	int x;
    // Ordinat
    int y;
}

// Kelas QuadNode sebagai penyimpan lokasi titik pusat simpul dan informasinya
class QuadNode{
	// Informasi yang disimpan oleh simpul
	int info;
    // Titik pusat simpul
	Point center;
}

// Kelas QuadTree sebagai representasi pohon berakar dan bercabang empat
class QuadTree{
	// Simpul akar dari pohon
	QuadNode root;
    // Titik yang menandai batas kiri atas dari daerah simpul
	Point topLeftBound;
    // Titik yang menandai batas kanan bawah dari daerah simpul
	Point bottomRightBound;
    // Cabang pertama sebagai representasi dari daerah kiri atas
	QuadTree topLeft;
    // Cabang kedua sebagai representasi dari daerah kanan atas
	QuadTree topRight;
    // Cabang ketiga sebagai representasi dari daerah kiri bawah
	QuadTree bottomLeft;
    // Cabang keempat sebagai representasi dari daerah kanan bawah
	QuadTree bottomRight;
 }
```
Selain *getter* dan *setter*, struktur data di atas memiliki fungsi primitif seperti :

- ```void insert(QuadNode Q)``` : Memasukkan sebuah simpul ke dalam *Quadtree*.
- ```int search(Point P)``` : Mencari sebuah titik pada simpul dengan daerah terkecil yang memiliki titik tersebut dalam *Quadtree*, dan mengembalikan informasi yang ada pada simpul yang ditemukan(jika ada).