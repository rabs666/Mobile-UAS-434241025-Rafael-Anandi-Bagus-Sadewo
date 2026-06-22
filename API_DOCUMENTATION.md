# Dokumentasi API - E-Ticketing Helpdesk UTS
**Versi:** 1.0.0  
**Project:** Native Android (Jetpack Compose)

Dokumentasi ini disusun untuk memenuhi syarat pengumpulan tugas (Langkah 14 pada panduan praktikum).

## 0. Informasi Umum

| Item | Nilai |
|------|-------|
| Base URL | `https://api.helpdesk-uts.ac.id` |
| Format data | JSON (`Content-Type: application/json`) |
| Autentikasi | Bearer token — header `Authorization: Bearer <token>` (kecuali login & register) |
| Application ID | `com.example.e_ticketinghelpdeskuts` |

**Format respons standar:**
```json
{ "success": true, "message": "string", "data": { } }
```
**Format error:**
```json
{ "success": false, "message": "Pesan kesalahan", "data": null }
```
Kode status: `200` OK, `201` Created, `400` Bad Request, `401` Unauthorized, `403` Forbidden, `404` Not Found.

## 1. Endpoint Autentikasi

### Login User
*   **Method:** `POST`
*   **URL:** `/api/auth/login`
*   **Body:** 
    ```json
    { "username": "string", "password": "string" }
    ```
*   **Akun Demo:** `ahmad` / `123456`

### Register User
*   **Method:** `POST`
*   **URL:** `/api/auth/register`
*   **Body:** 
    ```json
    { "name": "string", "username": "string", "email": "string", "password": "string" }
    ```

---

## 2. Endpoint Manajemen Tiket

### Ambil Daftar Tiket
*   **Method:** `GET`
*   **URL:** `/api/tickets`
*   **Keterangan:** Mengambil data tiket berdasarkan role (User hanya melihat tiket sendiri, Admin melihat semua).

### Buat Tiket Baru
*   **Method:** `POST`
*   **URL:** `/api/tickets`
*   **Body:** 
    ```json
    { "title": "string", "description": "string", "attachmentSource": "FILE/CAMERA" }
    ```

### Update Status Tiket (Admin/Helpdesk)
*   **Method:** `PATCH`
*   **URL:** `/api/tickets/{id}/status`
*   **Body:** `{ "status": "OPEN/IN_PROGRESS/CLOSED" }`

### Assign Petugas (Admin)
*   **Method:** `PATCH`
*   **URL:** `/api/tickets/{id}/assign`
*   **Body:** `{ "assignee": "string" }`

---

## 3. Komunikasi & Notifikasi

### Tambah Komentar/Balasan
*   **Method:** `POST`
*   **URL:** `/api/tickets/{id}/comments`
*   **Body:** `{ "message": "string" }`

### Get Notifikasi
*   **Method:** `GET`
*   **URL:** `/api/notifications`

---

## 4. Skema Data Model
Mengacu pada `domain/model/Ticket.kt`.

### Ticket
```json
{
  "id": "string",
  "title": "string",
  "description": "string",
  "status": "OPEN | IN_PROGRESS | CLOSED",
  "createdAt": "string (ISO-8601)",
  "applicantId": "string",
  "applicant": "string",
  "assignedTo": "string | null",
  "attachmentSource": "NONE | CAMERA | FILE",
  "attachmentName": "string | null",
  "comments": [ "Comment" ],
  "activities": [ "TicketActivity" ]
}
```

### Comment
```json
{ "id": "string", "sender": "string", "message": "string", "timestamp": "string" }
```

### TicketActivity
```json
{ "id": "string", "title": "string", "actor": "string", "timestamp": "string" }
```

### AppUser
```json
{ "id": "string", "name": "string", "username": "string", "email": "string", "role": "USER | HELPDESK | ADMIN" }
```

### AppNotification
```json
{ "id": "string", "title": "string", "message": "string", "timestamp": "string", "ticketId": "string | null", "isRead": false }
```

### Enum
| Enum | Nilai |
|------|-------|
| `TicketStatus` | `OPEN`, `IN_PROGRESS`, `CLOSED` |
| `UserRole` | `USER`, `HELPDESK`, `ADMIN` |
| `AttachmentSource` | `NONE`, `CAMERA`, `FILE` |

---

## Catatan Build Release (Modul 7)
- **Signing:** APK release ditandatangani dengan `keys.jks` (alias `key0`) via `signingConfigs`.
- **MinifyEnabled:** Aktif — obfuscation & shrinking kode (R8).
- **ShrinkResources:** Aktif — pembersihan resource tidak terpakai.
- **ProGuard:** `proguard-android-optimize.txt` + `proguard-rules.pro`.
- **Analyze APK:** dicek lewat `Build > Analyze APK` (Android Studio) atau `apkanalyzer` (CLI).
