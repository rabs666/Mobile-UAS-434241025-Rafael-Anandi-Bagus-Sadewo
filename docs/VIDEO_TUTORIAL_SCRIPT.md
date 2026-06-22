# Naskah / Storyboard Video Tutorial — E-Ticketing Helpdesk UTS

**Tujuan:** Video tutorial pemakaian aplikasi untuk lampiran UAS (Teori).
**Durasi target:** 6–9 menit.
**Format rekaman:** Screen recording emulator/HP (1080×1920 portrait) + voice-over.
**Tools rekam:** Android Studio Emulator + OBS Studio / `scrcpy` + perekam layar HP.

---

## 0. Persiapan Sebelum Rekam

- [ ] Install APK: `dist/ETicketingHelpdeskUTS-v1.0-release.apk`.
- [ ] Siapkan **akun demo** (password semua `123456`):
  - USER → `ahmad`
  - HELPDESK → `helpdesk`
  - ADMIN → `admin`
- [ ] Set emulator mode terang (light) untuk segmen awal, lalu demokan dark mode di Profil.
- [ ] Aktifkan "Show taps" di Developer Options agar sentuhan terlihat di video.
- [ ] Siapkan skrip voice-over (kolom **Narasi** di bawah).

---

## 1. Struktur Video (Ringkas)

| # | Segmen | Layar | Durasi |
|---|--------|-------|--------|
| 1 | Pembukaan & Splash | SplashScreen | 0:30 |
| 2 | Registrasi akun | RegisterScreen | 0:45 |
| 3 | Login | LoginScreen | 0:30 |
| 4 | Dashboard & statistik | DashboardScreen | 0:45 |
| 5 | Buat tiket | CreateTicketScreen | 1:00 |
| 6 | Daftar & detail tiket | TicketList / TicketDetail | 1:00 |
| 7 | Komentar / balasan | TicketDetailScreen | 0:45 |
| 8 | Notifikasi | NotificationScreen | 0:30 |
| 9 | Peran Helpdesk/Admin (ubah status & assign) | TicketDetail | 1:00 |
| 10 | Profil, dark mode, logout | ProfileScreen | 0:45 |
| 11 | Reset password | ResetPasswordScreen | 0:30 |
| 12 | Penutup | — | 0:30 |

---

## 2. Naskah per Segmen

### Segmen 1 — Pembukaan & Splash (0:30)
**Aksi:** Tampilkan ikon app di launcher → ketuk → Splash screen muncul dengan animasi → auto-pindah ke Login.
**Narasi:**
> "Halo, pada video ini saya akan mendemonstrasikan aplikasi **E-Ticketing Helpdesk UTS**, aplikasi Android untuk pengelolaan tiket bantuan teknis di kampus. Saat dibuka, aplikasi menampilkan splash screen lalu mengarahkan ke halaman login."

### Segmen 2 — Registrasi Akun Baru (0:45)
**Aksi:** Di Login, ketuk "Daftar" → isi Nama, Username, Email, Password, Konfirmasi Password → tunjukkan validasi real-time (mis. email salah / password < 6 karakter) → submit → kembali ke Login.
**Narasi:**
> "Pengguna baru dapat membuat akun lewat halaman Registrasi. Aplikasi memvalidasi input secara langsung — misalnya format email dan panjang password minimal 6 karakter. Setelah berhasil, akun otomatis berperan sebagai *User*."

### Segmen 3 — Login (0:30)
**Aksi:** Masukkan `ahmad` / `123456` → tunjukkan toggle lihat password → tombol loading → masuk Dashboard.
**Narasi:**
> "Sekarang saya login sebagai user dengan akun ahmad. Terdapat tombol untuk menampilkan password dan indikator loading saat proses autentikasi."

### Segmen 4 — Dashboard & Statistik (0:45)
**Aksi:** Sorot kartu statistik total tiket dan breakdown per status (Open / In Progress / Closed) → tunjukkan tombol aksi cepat dan menu sesuai role.
**Narasi:**
> "Setelah login, kita masuk ke Dashboard. Di sini terlihat ringkasan jumlah tiket beserta rinciannya berdasarkan status. Menu yang tampil menyesuaikan peran pengguna."

### Segmen 5 — Membuat Tiket (1:00)
**Aksi:** Ketuk "Buat Tiket" → isi Judul & Deskripsi → tunjukkan **character counter** dan pesan validasi (judul 5–100, deskripsi 10–1000) → pilih sumber lampiran (File/Kamera) + nama lampiran → submit → dialog sukses.
**Narasi:**
> "User dapat membuat tiket baru. Form ini memiliki validasi panjang judul dan deskripsi, penghitung karakter, serta opsi melampirkan berkas dari File atau Kamera. Setelah dikirim, muncul konfirmasi bahwa tiket berhasil dibuat."

### Segmen 6 — Daftar & Detail Tiket (1:00)
**Aksi:** Buka daftar tiket → tunjukkan filter/pencarian → ketuk satu tiket → tampilkan detail: status, pembuat, petugas, lampiran, riwayat aktivitas.
**Narasi:**
> "Semua tiket muncul dalam daftar yang bisa difilter dan dicari. Mengetuk satu tiket menampilkan detail lengkap, termasuk status, pembuat, petugas yang menangani, dan riwayat aktivitas tiket."

### Segmen 7 — Komentar / Balasan (0:45)
**Aksi:** Di detail tiket, ketik komentar di kolom bawah → kirim → komentar muncul di daftar, aktivitas bertambah otomatis.
**Narasi:**
> "Pada setiap tiket terdapat fitur diskusi. Komentar yang dikirim langsung tampil dan tercatat dalam riwayat aktivitas tiket."

### Segmen 8 — Notifikasi (0:30)
**Aksi:** Buka ikon notifikasi (tunjukkan badge unread) → lihat daftar notifikasi → ketuk "Tandai semua dibaca" → badge hilang → ketuk notifikasi untuk lompat ke tiket terkait.
**Narasi:**
> "Setiap aktivitas penting memunculkan notifikasi dengan penanda belum dibaca. Pengguna bisa menandai semua sebagai terbaca, atau menekan notifikasi untuk langsung membuka tiket terkait."

### Segmen 9 — Peran Helpdesk & Admin (1:00)
**Aksi:** Logout → login sebagai `admin` / `123456` → buka tiket `T-001` → **ubah status** ke IN_PROGRESS → **assign** ke petugas helpdesk → tunjukkan aktivitas & notifikasi yang muncul.
**Narasi:**
> "Berbeda dengan user, peran Admin dan Helpdesk dapat mengelola tiket: mengubah status dan menugaskan tiket ke petugas. Setiap perubahan otomatis tercatat di riwayat aktivitas dan memicu notifikasi."

### Segmen 10 — Profil, Dark Mode & Logout (0:45)
**Aksi:** Buka Profil → tunjukkan data akun → aktifkan **toggle Dark Mode** (UI berubah gelap) → ketuk Logout → konfirmasi → kembali ke Login.
**Narasi:**
> "Di halaman Profil tersedia data akun, pengaturan mode gelap, dan tombol keluar. Mode gelap mengubah seluruh tampilan aplikasi. Logout mengembalikan kita ke halaman login."

### Segmen 11 — Reset Password (0:30)
**Aksi:** Di Login → ketuk "Lupa Password" → masukkan email → submit → pesan instruksi terkirim.
**Narasi:**
> "Jika lupa password, pengguna dapat memintanya lewat halaman Reset Password dengan memasukkan email terdaftar."

### Segmen 12 — Penutup (0:30)
**Aksi:** Tampilkan kembali Dashboard / logo app.
**Narasi:**
> "Demikian demonstrasi aplikasi E-Ticketing Helpdesk UTS, mencakup autentikasi, manajemen tiket, komentar, notifikasi, serta peran berbeda untuk User, Helpdesk, dan Admin. Terima kasih."

---

## 3. Checklist Fitur yang Wajib Tampil di Video

- [ ] Splash screen
- [ ] Registrasi + validasi
- [ ] Login (3 role berbeda)
- [ ] Dashboard + statistik
- [ ] Buat tiket + validasi + lampiran
- [ ] Daftar tiket + filter/cari
- [ ] Detail tiket + riwayat aktivitas
- [ ] Komentar
- [ ] Notifikasi + badge + tandai dibaca
- [ ] Ubah status & assign (Admin/Helpdesk)
- [ ] Dark mode
- [ ] Logout
- [ ] Reset password

---

## 4. Tips Produksi

- Rekam tiap segmen terpisah agar mudah retake; gabungkan di editor (CapCut/DaVinci Resolve).
- Tambahkan teks judul segmen di pojok layar.
- Gunakan suara jelas; bila tanpa voice-over, tampilkan caption teks narasi.
- Ekspor 1080p, 30fps, format MP4.
- Unggah ke YouTube (unlisted) atau Google Drive, sertakan tautannya di README repository.
