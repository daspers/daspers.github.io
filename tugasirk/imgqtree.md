# Image Compression with Quadtree

Tujuan dari algoritma compresi ini adalah memecah suatu gambar menjadi upa-gambar 
dan disimpan kesimpul dari Quadtree yang tiap upa-gambar-nya memiliki suatu kesamaan yang telah ditetapkan
menggunakan algoritma Divide and Conquer.

Berikut algoritma kompresi:
1. Diberikan sebuah gambar `X0`.
2. Bagi gambar `X0` menjadi 4 upa-gambar `X1`, `X2`, `X3`, dan `X4`.
3. Untuk setiap upa-gambar `Xi`, hitung nilai error `Ei` dari upa-gambar tersebut.
4. Jika nilai error `Ei` lebih besar dari batas maksimum Error `Limit`, bagi upa-gambar menjadi 4 upa-gambar yang lebih kecil.
5. Jika nilai error `Ei` kurang dari sama dengan maksimum error `Limit`, simpan rata-rata nilai `RGB` upa-gambar tersebut.
6. Ulangi dari langkah ke-3 hingga semua nilai error `Ei` upa-gambar sudah dalam batas maksimum error.

Semakin besar batas nilai error yang digunakan, semakin tidak sesuai gambar hasil kompresi dengan gambar aslinya. Jika nilai error lebih dari 0, maka disebut lossy compression.

Perhitungan nilai error dapat dilakukan dengan banyak cara.