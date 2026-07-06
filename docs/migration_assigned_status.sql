-- ============================================================
--  MIGRATION: Tambah status ASSIGNED ke tabel tickets (Supabase/PostgreSQL)
--
--  Kenapa perlu: aplikasi versi sekarang memakai 4 status
--  (OPEN, ASSIGNED, IN_PROGRESS, CLOSED). Jika tabel `tickets`
--  di Supabase dibuat dengan skema lama (3 status), maka CHECK
--  constraint akan MENOLAK update status = 'ASSIGNED'.
--
--  Cara pakai:
--  1. Buka dashboard Supabase > menu "SQL Editor".
--  2. Copy seluruh isi file ini, paste, lalu tekan "Run".
--  3. Aman dijalankan berulang (idempotent) & TIDAK menghapus data.
-- ============================================================

-- LANGKAH 1 (opsional) — cek dulu constraint status yang ada sekarang:
--   SELECT conname, pg_get_constraintdef(oid) AS definisi
--   FROM pg_constraint
--   WHERE conrelid = 'tickets'::regclass AND contype = 'c';

-- LANGKAH 2 — drop constraint lama (nama default: tickets_status_check).
--   Jika di LANGKAH 1 namanya berbeda, ganti nama di bawah ini.
ALTER TABLE tickets DROP CONSTRAINT IF EXISTS tickets_status_check;

-- LANGKAH 3 — pasang constraint baru dengan 4 status.
ALTER TABLE tickets
  ADD CONSTRAINT tickets_status_check
  CHECK (status IN ('OPEN', 'ASSIGNED', 'IN_PROGRESS', 'CLOSED'));

-- LANGKAH 4 (opsional) — seed 1 tiket berstatus ASSIGNED untuk screenshot laporan.
--   Lewati jika tidak perlu. Aman diulang berkat ON CONFLICT.
INSERT INTO tickets
  (id, title, description, status, created_at, applicant_id, assigned_to, attachment_source, attachment_name)
VALUES
  ('T-004','Printer Ruang Dosen Error','Printer tidak terdeteksi oleh komputer di ruang dosen.',
   'ASSIGNED','2026-04-09 08:00','U-002', NULL, 'NONE', NULL)
ON CONFLICT (id) DO NOTHING;

INSERT INTO ticket_activities (id, ticket_id, title, actor, created_at) VALUES
  ('A-008','T-004','Tiket dibuat','Siti Aminah','2026-04-09 08:00'),
  ('A-009','T-004','Tiket diterima oleh admin — status Assigned','Admin UTS','2026-04-09 08:15')
ON CONFLICT (id) DO NOTHING;

-- LANGKAH 5 (verifikasi) — pastikan constraint baru sudah 4 status:
--   SELECT pg_get_constraintdef(oid)
--   FROM pg_constraint
--   WHERE conrelid = 'tickets'::regclass AND conname = 'tickets_status_check';
