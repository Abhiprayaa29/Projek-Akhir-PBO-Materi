# Projek Akhir Pemrograman Berorientasi Objek (PBO)

Repositori ini berisi proyek akhir untuk mata kuliah Pemrograman Berorientasi Objek (PBO). Aplikasi ini dibangun menggunakan bahasa pemrograman **Java** dengan menerapkan prinsip-prinsip utama OOP serta arsitektur kode yang bersih dan terstruktur.

## 🚀 Fitur Utama
*   **Autentikasi & Otorisasi:** Sistem manajemen masuk (Login) yang aman untuk pengguna.
*   **Manajemen Data (CRUD):** Pengelolaan data entitas secara dinamis dan efisien.
*   **Penanganan Pengecualian (Exception Handling):** Implementasi *custom exception* untuk memvalidasi alur logika sistem (seperti validasi saldo, ketersediaan kapasitas, dll).
*   **Arsitektur Data Access Object (DAO):** Pemisahan logika bisnis aplikasi dengan manipulasi database menggunakan design pattern DAO.
*   **Antarmuka Grafis (GUI):** Menggunakan komponen Java Swing untuk interaksi pengguna yang intuitif dan responsif.

## 🛠️ Teknologi yang Digunakan
*   **Bahasa Pemrograman:** Java
*   **IDE:** Apache NetBeans
*   **Database:** MySQL / MariaDB (melalui konektor JDBC)
*   **Version Control:** Git & GitHub

## 📂 Struktur Proyek
```text
├── nbproject/           # Konfigurasi internal proyek NetBeans
├── src/                 # Kode sumber (source code) utama aplikasi
│   ├── controller/      # Mengatur alur logika bisnis dan komunikasi data
│   ├── exception/       # Custom exception handling untuk proteksi sistem
│   ├── model/           # Kelas entitas, Data Access Object (DAO), dan koneksi database
│   └── view/            # Desain antarmuka grafis (Java Swing / JFrame)
├── .gitignore           # Daftar file/folder yang diabaikan oleh Git
└── build.xml            # Skrip build otomatis ant untuk proyek
