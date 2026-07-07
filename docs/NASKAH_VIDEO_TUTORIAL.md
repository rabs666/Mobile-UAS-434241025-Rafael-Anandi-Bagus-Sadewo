# Naskah Video Tutorial / Walkthrough — E-Ticketing Helpdesk UTS (Update Terbaru)

**Durasi Estimasi:** 3-5 Menit
**Target Audiens:** Dosen Penilai UAS Mobile (Teori & Praktikum)

---

## [00:00 - 00:30] Opening & Intro
**(Visual: Menampilkan Splash Screen aplikasi dan kemudian halaman Login)**

**Narator/Presenter:**
"Halo, selamat datang di demonstrasi aplikasi E-Ticketing Helpdesk untuk tugas UAS Mobile. Pada versi terbaru ini, kami telah melakukan beberapa *major update*! Aplikasi yang sebelumnya menggunakan data *dummy* *in-memory*, sekarang sudah sepenuhnya terintegrasi dengan **Backend Supabase (PostgreSQL)** secara *real-time*."

"Selain itu, kami juga menambahkan beberapa alur baru seperti penambahan status tiket `ASSIGNED` dan fitur **Kelola Pengguna** khusus untuk *Admin*."

**(Visual: Menunjukkan tabel di dashboard Supabase sekilas - opsional)**
"Ini adalah tampilan *database* Supabase kami yang menyimpan data *users*, *tickets*, *comments*, dan *activities*."

---

## [00:30 - 01:15] Skenario 1: User (Pelapor) Membuat Tiket Baru
**(Visual: Login menggunakan akun User, misal username: `ahmad`, password: `123456`)**

**Narator:**
"Mari kita mulai dengan *login* sebagai **User biasa** atau pelapor. Di *Dashboard*, User bisa melihat ringkasan tiket yang ia buat."

**(Visual: Klik FAB / menu Buat Tiket. Isi Form: Judul "Proyektor Rusak", Deskripsi "Proyektor di ruang 101 tidak menyala". Pilih file lampiran jika ada. Klik Simpan.)**

**Narator:**
"User membuat tiket baru. Setelah dikirim, data tiket akan masuk ke Supabase dan otomatis memiliki status awal yaitu **OPEN**."

**(Visual: Buka Detail Tiket yang baru dibuat)**
"Di *Detail Tiket*, kita bisa melihat *timeline* aktivitas yang mencatat kapan tiket ini dibuat."

---

## [01:15 - 02:00] Skenario 2: Admin Menerima & Assign Tiket
**(Visual: Logout dari Ahmad. Login menggunakan akun Admin, username: `admin`, password: `123456`)**

**Narator:**
"Sekarang kita *login* sebagai **Admin**. Admin memiliki hak akses penuh, termasuk melihat seluruh tiket yang masuk."

**(Visual: Buka tiket "Proyektor Rusak" yang tadi dibuat Ahmad yang masih berstatus OPEN)**

**Narator:**
"Admin membuka tiket baru tersebut. Pada *update* terbaru, kami menambahkan siklus baru. Tiket tidak langsung di-*progress*, melainkan Admin bisa menerimanya terlebih dahulu dan mengubah statusnya menjadi **ASSIGNED**."

**(Visual: Admin klik tombol 'Terima Tiket' atau ubah status ke ASSIGNED, lalu memilih petugas Helpdesk, misal `arif`)**

**Narator:**
"Admin melakukan *assign* tiket ini ke petugas Helpdesk bernama Arif. *Audit trail* atau *timeline* langsung mencatat perubahan status dan petugas yang di-*assign*."

---

## [02:00 - 02:45] Skenario 3: Helpdesk Menangani Tiket
**(Visual: Logout dari Admin. Login menggunakan akun Helpdesk, username: `arif`, password: `123456`)**

**Narator:**
"Selanjutnya, petugas **Helpdesk** (Arif) *login*. Ia akan melihat notifikasi atau melihat daftar tiket bahwa ada tugas baru untuknya."

**(Visual: Arif membuka tiket "Proyektor Rusak". Mengubah status menjadi IN_PROGRESS)**

**Narator:**
"Arif mulai mengecek, dan mengubah status tiket menjadi **IN PROGRESS**. Arif juga bisa berdiskusi dengan *User*."

**(Visual: Arif menulis komentar "Sedang saya cek ke ruangan 101 ya, mohon tunggu.")**

"Fitur komentar ini juga tersimpan di Supabase dan bisa dilihat secara *real-time* oleh pelapor."

**(Visual: Arif mengubah status menjadi CLOSED)**

"Setelah selesai diperbaiki, Arif mengubah status tiket menjadi **CLOSED**, yang menandakan tiket telah selesai ditangani."

---

## [02:45 - 03:30] Skenario 4: Fitur Ekstra (Kelola Pengguna & Dark Mode)
**(Visual: Logout dari Arif. Login kembali sebagai Admin. Buka menu Kelola Pengguna)**

**Narator:**
"Sebagai tambahan *update* terbaru, kami menambahkan layar **Kelola Pengguna (User Management)** khusus untuk Admin."

**(Visual: Admin melihat daftar pengguna. Admin mencoba menambahkan user baru atau mengubah role user lain)**

"Di sini, Admin bisa menambah pengguna baru secara langsung, menghapus pengguna, atau mengubah *Role* pengguna, misalnya menaikkan User biasa menjadi Helpdesk. Semua perubahan ini langsung tersinkronisasi ke tabel `users` di Supabase."

**(Visual: Buka menu Profil. Toggle Dark Mode diaktifkan)**

"Terakhir, aplikasi ini juga sudah mendukung **Dark Mode** secara penuh menggunakan komponen *Jetpack Compose Material 3*."

---

## [03:30 - 04:00] Penutup
**(Visual: Menampilkan Dashboard dalam keadaan Dark Mode)**

**Narator:**
"Demikian *walkthrough* dari versi terbaru E-Ticketing Helpdesk. Dengan transisi ke **Supabase**, penambahan siklus status **ASSIGNED**, dan **Manajemen Pengguna**, aplikasi ini sudah memiliki *flow* *helpdesk* yang lengkap dan siap rilis. Terima kasih."
