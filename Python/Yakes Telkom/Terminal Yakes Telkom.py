

from prettytable import PrettyTable
import mysql.connector  
import time
from datetime import datetime
import pygame
import matplotlib.pyplot as plt
import uuid

myconn = mysql.connector.connect(user='root', password='',
                                 host='localhost',
                                 database='klinik_yakes_telkom')

curr = myconn.cursor()
nomor_sekarang = 0
antrian = []

def alarm_0205():
    alarm_time = input("Masukkan waktu konsultasi anda JJ:MM: ")
    sound_file = 'iphone_alarm.mp3'

    pygame.mixer.init()

    while True:
        current_time = datetime.now().strftime("%H:%M")
        if current_time == alarm_time:
            print("Antrian Anda Sudah dipanggil Mohon Bersiap!")
            pygame.mixer.music.load(sound_file)
            pygame.mixer.music.play()
            while pygame.mixer.music.get_busy():
                time.sleep(1)
            break
        time.sleep(30)
    print ('='*50)
    print ('Selamat Berkonsultasi')  

def tambah_antrian_0205():
    global nomor_sekarang
    nomor_sekarang += 1
    antrian.append(nomor_sekarang)
    print(f"Nomor antrian anda {nomor_sekarang}")

def panggil_antrian_0205():
    panggil = int(input('Masukkan antrian yang ingin di panggil : '))
    panggill = panggil - 1 
    if antrian:
        nomor_dipanggil = antrian.pop(panggill)
        print(f"Nomor antrian {nomor_dipanggil} dipanggil.")
    else:
        print("Tidak ada antrian yang menunggu.")

def dokter_baru_0205 ():
    ID_dokter = str(input('Masukkan ID dokter anda : '))
    nama = str(input('Masukkan nama anda : '))
    spesialis = str(input('Masukkan keahlian anda : '))
    jam = str(input('Masukkan jam praktek anda : '))
    
    sql = "INSERT INTO dokter(ID_dokter,nama,spesialis,jam_praktek) VALUES (%s,%s,%s,%s)"
    
    curr.execute(sql,(ID_dokter,nama,spesialis,jam))
    
    print('Data anda sukses dimasukkan!')
    myconn.commit()

def gudang_baru_0205 ():
    ID_obat = str(input('Masukkan ID_obat : '))
    nama_obat = str(input('Masukkan nama obat : '))
    stok_obat = int(input('Masukkan stok obat : '))
    
    sql = "INSERT INTO gudang(ID_obat,nama_obat,stok_obat)VALUES(%s,%s,%s)"
    
    curr.execute(sql,(ID_obat,nama_obat,stok_obat))
    
    print('Data anda sukses dimasukkan!')
    myconn.commit()

def pasien_baru_0205 ():
    
    NIK_pasien = int(input('Masukkan NIK anda : '))
    nama = str(input('Masukkkan nama anda : '))
    email = str(input('Masukkan email anda : '))
    nomor = int(input('Masukkan Nomor anda : '))
    
    sql = "INSERT INTO pasien(NIK,nama,email,nomor_telepon) VALUES (%s,%s,%s,%s)"
    
    curr.execute(sql,(NIK_pasien,nama,email,nomor))
    
    print('Data anda sukses dimasukkan!')
    myconn.commit()


def masukkan_data_konsul_0205 ():
    data_dokter_0205()
    NIK_yakes = int(input('Masukkan NIK pasien Yakes_Telkom : '))
    ID_dokter = str(input('Masukkan ID_dokter anda : '))
    tanggal = str(input('Masukkan  tanggal : '))
    note = str(input('Periksa Online/Offline : '))
    
    sql = "INSERT INTO konsultasi(NIK,ID_dokter,tanggal,note) VALUES (%s,%s,%s,%s)"
    curr.execute(sql, (NIK_yakes,ID_dokter,tanggal,note))
    myconn.commit()
    if note == 'Offline':
        tambah_antrian_0205()
        kodeqr=str(uuid.uuid4())
        print (f'Kode antrian anda : {kodeqr}')
        print ('Silahkan menunggu antrian')
    elif note == 'Online':
        alarm_0205()
        
def data_dokter_0205 ():
    sql1 = "SELECT * FROM `dokter`"
    curr.execute (sql1)
    result = curr.fetchall()
    collumns = [description[0]for description in curr.description]
    table = PrettyTable(collumns)
    for row in result:
        table.add_row(row)
    print ('Data Dokter')
    print(table)
    myconn.commit()


def data_pasien_0205 ():
    sql1 = "SELECT * FROM `pasien`"
    curr.execute (sql1)
    result = curr.fetchall()
    collumns = [description[0]for description in curr.description]
    table = PrettyTable(collumns)
    for row in result:
        table.add_row(row)
    print ('Data Pasien')
    print(table)
    myconn.commit()


def data_konsultasi_0205 ():
    sql1 = "SELECT * FROM `konsultasi`"
    curr.execute (sql1)
    result = curr.fetchall()
    collumns = [description[0]for description in curr.description]
    table = PrettyTable(collumns)
    for row in result:
        table.add_row(row)
    print ('Data Konsultasi')
    print(table)
    myconn.commit()
    
def data_gudang_0205 ():
    sql = "SELECT * FROM `gudang`"
    curr.execute(sql)
    result = curr.fetchall()
    collumns = [description[0]for description in curr.description]
    table = PrettyTable(collumns)
    for row in result:
        table.add_row(row)
    print ('Data Gudang')
    print(table)
    myconn.commit()

def hapus_data_pasien_0205 ():
    
    NIK_pasien = int(input('Masukkan NIK yang akan di hapus : '))
    sql = 'DELETE FROM pasien WHERE NIK = %s'
    
    curr.execute(sql,(NIK_pasien,))
    print (f'Data {NIK_pasien} sudah di hapus!')
    myconn.commit()

def hapus_data_dokter_0205 ():
    ID_dokter = str(input('Masukkan ID Dokter yang akan di hapus : '))
    sql = 'DELETE FROM dokter WHERE ID_dokter = %s'    
    
    curr.execute(sql,(ID_dokter,))
    print (f'Data {ID_dokter} sudah di hapus!')
    myconn.commit()

def hapus_data_konsul_0205 ():
    NIK_pasien = str(input('Masukkan ID Dokter yang akan di hapus : '))
    sql = 'DELETE FROM konsultasi WHERE NIK = %s'    
    
    curr.execute(sql,(NIK_pasien,))
    print (f'Data {NIK_pasien} sudah di hapus!')
    myconn.commit()

def hapus_data_gudang_0205 ():
    ID_obat = str(input('Masukkan ID_obat : '))
    sql = "DELETE FROM gudang WHERE ID_obat = %s"
    
    curr.execute(sql, (ID_obat,))
    print (f'Data {ID_obat} sudah di hapus!')
    myconn.commit()

def mengubah_table_pasien_0205 ():
    NIK_pasien = int(input('Masukkan NIK pasien : '))
    NIK_pasien_baru = int(input('Masukkan NIK baru anda : '))
    nama = str(input("Masukkan nama anda : "))
    email = str(input('Masukkan email anda : '))
    nomor_telp = int(input('Masukkan nomor telepon anda : '))
    
    sql = "UPDATE pasien SET NIK = %s,nama = %s,email = %s,nomor_telepon = %s  WHERE NIK = %s  "
    
    curr.execute(sql,(NIK_pasien_baru,nama,email,nomor_telp,NIK_pasien))
    
    print ('Data anda berhasil di update! ')
    myconn.commit()


def mengubah_table_dokter_0205 ():
    ID_lama = str(input('Masukkan ID anda : '))
    ID_baru = str(input('Masukkan ID baru anda : '))
    nama_baru = str(input('Masukkan nama baru anda : '))
    spesialis = str(input('Masukkan nama spesialis : '))
    jam = str(input('Masukkan jam praktek anda : '))
    
    sql = "UPDATE dokter SET ID_dokter = %s ,nama = %s,spesialis = %s,jam_praktek = %s WHERE ID_dokter = %s "
    
    curr.execute(sql,(ID_baru,nama_baru,spesialis,jam,ID_lama))
    
    print ('Data anda berhasil di update! ')
    myconn.commit()

def mengubah_table_gudang_0205 ():
    ID_obat = str(input('Masukkan ID anda : '))
    ID_baru = str(input('Masukkan ID baru : '))
    nama_baru = str(input('Masukkan nama obat : '))
    stok = int(input('Masukkan jumlah stok obat : '))
    
    sql = "UPDATE gudang SET ID_obat = %s ,nama_obat = %s,stok_obat = %s WHERE ID_obat = %s "
    
    curr.execute(sql,(ID_baru,nama_baru,stok,ID_obat))

    print ('Data anda berhasil di update! ')
    myconn.commit()

def mengubah_table_konsul_0205 ():
    NIK_pasien = str(input('Masukkan NIK anda : '))
    ID_dokter = str(input('Masukkan ID_dokter anda : '))
    tanggal = str(input('Masukkan tanggal konsultasi : '))
    note = int(input('Masukkan apakah Online/Offline : '))
    
    sql = "UPDATE gudang SET NIK = %s ,ID_dokter = %s,tanggal = %s,note = %s WHERE NIK = %s "
    
    curr.execute(sql,(NIK_pasien,ID_dokter,tanggal,note,NIK_pasien))

    print ('Data anda berhasil di update! ')
    myconn.commit()
    
def mencari_dokter_0205 ():
    dokter = str(input('Masukkan nama dokter: '))

    sql = "SELECT * FROM dokter WHERE nama LIKE %s"
    tambahan = ('%' + dokter + '%',)
    
    print('Hasil Pencarian anda : ')
    curr.execute(sql,tambahan)
    result = curr.fetchall()
    
    columns = [description[0] for description in curr.description]

    table = PrettyTable(columns)
    for row in result:
        table.add_row(row)
        
        print(table)
    
    curr.close()
    myconn.commit()

def mencari_pasien_0205 ():
    pasien = str(input('Masukkan nama pasien : '))
    
    sql = "SELECT * FROM pasien WHERE nama LIKE %s"
    tambah = ('%' + pasien + '%',)
    
    print('Hasil Pencarian anda : ')
    curr.execute(sql,tambah)
    
    result = curr.fetchall()
    
    columns = [description[0] for description in curr.description]
    
    table = PrettyTable(columns)
    
    for row in result:
        table.add_row(row)
        
        print(table)
    
    myconn.commit()
    
def mencari_obat_0205():
    gudang = str(input('Masukkan nama obat : '))
    
    sql = "SELECT * FROM gudang WHERE ID_obat LIKE %s"
    tambah = ('%' + gudang + '%',)
    
    print('Hasil Pencarian anda : ')
    curr.execute(sql,tambah)
    
    result = curr.fetchall()
    
    columns = [description[0] for description in curr.description]
    
    table = PrettyTable(columns)
    
    for row in result:
        table.add_row(row)
        
        print(table)
    
    myconn.commit()
    
def mencari_konsul_0205():
    konsul = str(input('Masukkan nama konsultasi : '))
    
    sql = "SELECT * FROM konsultasi WHERE ID_obat LIKE %s"
    tambah = ('%' + konsul + '%',)
    
    print('Hasil Pencarian anda : ')
    curr.execute(sql,tambah)
    
    result = curr.fetchall()
    
    columns = [description[0] for description in curr.description]
    
    table = PrettyTable(columns)
    
    for row in result:
        table.add_row(row)
        print(table)
    
    myconn.commit()

def diagram_0205 ():
    
    curr.execute ('SELECT nama_obat,SUM(stok_obat) FROM gudang GROUP BY nama_obat')
    
    data = curr.fetchall()
    
    genre = [row[0]for row in data]
    stok = [row[1]for row in data]
    
    plt.bar (genre,stok,color='blue')
    
    plt.title('Jumlah stok berdasarkan genre buku')
    plt.xlabel('nama_obat')
    plt.ylabel('jumlah_stok')
    
    plt.show()   
    


while True : 
    print ('='*50)
    print('SELAMAT DATANG DI KLINIK YAKES TELKOM')
    print('Masukkan menu untuk : ')
    print('1. Pasien')
    print('2. Admin ')
    print('0. Keluar')
    print ('='*50)
    menu1 = int(input('Masukkan pilihan menu : '))
    print ('='*50)
    if menu1 == 1 :
        masukkan_data_konsul_0205()
    elif menu1 == 2:
        password = 12345
        mas_pass = int(input('Masukkan Password anda : '))
        if mas_pass == 12345 :
            print ('='*50)
            print ("Silahkan pilih menu : ")
            print ('1. Buat data baru')
            print ('2. Edit data ')
            print ('3. Hapus data')
            print ('4. Cari data')
            print ('5. Show data ')
            print ('6. Data Visualisasi')
            print ('7. Panggil antrian')
            print ('='*50)
            menu2 = int(input("Masukkan pilihan menu : "))
            print ('='*50)
            if menu2 == 1:
                print ('List data : ')
                print ('1. pasien ')
                print ('2. Dokter')
                print ('3. obat')
                print ('='*50)
                menu3 = int(input('Masukkan data yang ingin di buat : '))
                if menu3 == 1 :
                    pasien_baru_0205()
                elif menu3 == 2 :
                    dokter_baru_0205()
                elif menu3 == 3:
                    gudang_baru_0205()
            elif menu2 == 2 : 
                print ('List menu : ')
                print ('1. pasien ')
                print ('2. Dokter')
                print ('3. Obat')
                print ('4. Konsultasi')
                print ('='*50)
                menu4 = int(input('Masukkan data yang ingin di edit : '))
                if menu4 == 1:
                    mengubah_table_pasien_0205()
                elif menu4 == 2 :
                    mengubah_table_dokter_0205()
                elif menu4 == 3 :
                    mengubah_table_gudang_0205()
                elif menu4 == 4: 
                    mengubah_table_konsul_0205()
            elif menu2 == 3 :
                print ('List menu : ')
                print ('1. pasien ')
                print ('2. Dokter')
                print ('3. Obat')
                print ('4. Konsultasi')
                print ('='*50)
                menu5 = int(input('Masukkan data yang ingin di hapus : ')) 
                if menu5 == 1 : 
                    hapus_data_pasien_0205()
                elif menu5 == 2 :
                    hapus_data_dokter_0205()
                elif menu5 == 3 :
                    hapus_data_gudang_0205()
                elif menu5 == 4 :
                    hapus_data_konsul_0205()
            elif menu2 == 4:
                print ('List menu : ')
                print ('1. pasien ')
                print ('2. Dokter')
                print ('3. Obat')
                print ('4. Konsultasi')
                print ('='*50)
                menu5 = int(input('Masukkan data yang ingin di cari : ')) 
                if menu5 == 1 : 
                    mencari_pasien_0205()
                elif menu5 == 2 :
                    mencari_dokter_0205()
                elif menu5 == 3 :
                    mencari_obat_0205()
                elif menu5 == 4 :
                    mencari_konsul_0205()
            elif menu2 == 5:
                print ('List menu : ')
                print ('1. pasien ')
                print ('2. Dokter')
                print ('3. obat')
                print ('4. Konsultasi')
                print ('='*50)
                menu6 = int(input('Masukkan data yang ingin di lihat : '))
                if menu6 == 1 :
                    data_pasien_0205()
                elif menu6 == 2 :
                    data_dokter_0205()
                elif menu6 == 3 : 
                    data_gudang_0205()
                elif menu6 == 4 : 
                    data_konsultasi_0205()
            elif menu2 == 6 :
                diagram_0205()
                
            elif menu2 == 7 :
                panggil_antrian_0205()
                
        else : 
            print('Pasword yang anda masukkan salah,silahkan ulang lagi !! ')
            pass 
    elif menu1 == 0 :
        print('Terima kasih telah menggunakan program ini :)')
        print ('='*50)
        break
                