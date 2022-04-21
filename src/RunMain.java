import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RunMain {
	public static List<SanPham> listSanPhams= new ArrayList<SanPham>();
	public static List<TaiKhoan> listTaiKhoans= new ArrayList<TaiKhoan>();
	public static Scanner sc= new Scanner(System.in);
	public static Connection conn;
	public static void main(String[] args) {
		generateDB();
		yeuCauDangNhap();
		int choice;
		do {
			System.out.println("========== MENU =======");
			System.out.println("1. Thêm sản phẩm");
			System.out.println("2. Hiển thị danh sách sản phẩm");
			System.out.println("3. Xoá sản phẩm theo id");
			System.out.println("4. Sửa sản phẩm theo id");
			System.out.println("0. Thoát");
			System.out.println("Nhập lựa chọn: ");
			choice= sc.nextInt();
			switch (choice) {
			case 1:
				themSanPham();
				break;
			case 2:
				hienThiSanPham();
				break;
			case 3:
				xoaSanPham();
				break;
			case 4: 
				suaSanPham();
				break;
			case 0:
				System.out.println("Ket thuc chuong trinh");
				System.exit(0);
				break;
			default:
				System.out.println("Lua chon khong phu hop. Hay nhap lai!!");
				break;
			}
		} while (choice!=0);
		
	}
	
	private static void yeuCauDangNhap() {
		boolean check=true;
		do {
			System.out.println("===== ĐĂNG NHẬP =====");
			System.out.println("Nhập tài khoản: ");
			String user = sc.nextLine();
			System.out.println("Nhập mật khẩu: ");
			String pass= sc.nextLine();
			for (TaiKhoan tk: listTaiKhoans) {
				if (tk.getUsername().compareToIgnoreCase(user)==0 && tk.getPassword().compareToIgnoreCase(pass)==0) {
					System.out.println("ĐĂNG NHẬP THÀNH CÔNG.!!!");
					check= false;
					break;
				}
			}
			if (check) {
				System.out.println("SAI TÀI KHOẢN HOẶC MẬT KHẨU!! HÃY THỬ LẠI....");
			}
		} while (check);
		
	}

	// 4. Sửa sản phẩm
	private static void suaSanPham() {
		// TODO Auto-generated method stub
		System.out.println("Nhập mã sản phẩm cần sửa: ");
		sc.nextLine();
		String idString= sc.nextLine();
		boolean check= true;
		for(int i=0; i<listSanPhams.size(); i++) {
			if(listSanPhams.get(i).getMaSP().compareTo(idString)==0) {
				System.out.println("Nhập tên sản phẩm: ");
				String ten= sc.nextLine();
				System.out.println("Nhập giá: ");
				long gia= sc.nextLong();
				sc.nextLine();
				System.out.println("Nhập loại: ");
				String loai= sc.nextLine();
				System.out.println("Nhập màu sắc: ");
				String mau= sc.nextLine();
				PreparedStatement pstmt;
				try {
					pstmt = (PreparedStatement) conn.prepareStatement("update sanpham set tensp=?, gia=?, loai=?, mausac=? where masp=?");
					pstmt.setString(1, ten);
					pstmt.setLong(2, gia);
					pstmt.setString(3, loai);
					pstmt.setString(4, mau);
					pstmt.setString(5, idString);
			        pstmt.executeUpdate();
			        pstmt.close();
			        listSanPhams.get(i).setTenSP(ten);
			        listSanPhams.get(i).setGiaThanh(gia);
			        listSanPhams.get(i).setLoai(loai);
			        listSanPhams.get(i).setMauSac(mau);
			        System.out.println("Sửa sản phẩm thành công");
			        check= false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}	
				check= false;
				break;
			}
		}
		if (check) {
			System.out.println("Không tìm thấy mã sản phẩm: "+ idString);
		}

	}


	private static void xoaSanPham() {
		System.out.println("Nhập mã sản phẩm cần xoá: ");
		sc.nextLine();
		String idString= sc.nextLine();
		boolean check=true;
		for (int i=0; i<listSanPhams.size(); i++) {
			if (listSanPhams.get(i).getMaSP().compareTo(idString)==0) {
				listSanPhams.remove(i);
				PreparedStatement pstmt;
				try {
					pstmt = (PreparedStatement) conn.prepareStatement("delete from sanpham where masp=?");
					pstmt.setString(1, idString);
			        pstmt.executeUpdate();
			        pstmt.close();
			        System.out.println("Xoá sản phẩm thành công");
			        check= false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}	
				break;
			}
			
		}
		if (check) {
			System.out.println("Không tìm thấy mã sản phẩm: "+ idString);
		}
		
	}

	//2. hiển thị sản phẩm
	private static void hienThiSanPham() {
		for (SanPham sanPham : listSanPhams) {
			System.out.println(sanPham);
		}
		
	}

	//1. Thêm sản phẩm
	private static void themSanPham() {
		System.out.println("==== THEM SAN PHAM ====");
		sc.nextLine();
		System.out.println("Nhập tên sản phẩm: ");
		String tenSP= sc.nextLine();
		System.out.println("Nhập giá sản phẩm: ");
		long gia= sc.nextLong();
		sc.nextLine();
		System.out.println("Nhập loại sản phẩm: ");
		String loai= sc.nextLine();
		System.out.println("Nhập màu sắc: ");
		String mauSac= sc.nextLine();
		SanPham sPham= new SanPham(listSanPhams.size()+"", tenSP, gia, loai, mauSac);
		listSanPhams.add(sPham);
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("insert into sanpham(masp,tensp,gia,loai,mausac) values(?,?,?,?,?)");
			pstmt.setString(1, "2");
			pstmt.setString(2, tenSP);
	        pstmt.setLong(3, gia);
	        pstmt.setString(4, loai);
	        pstmt.setString(5, mauSac);
	        //same for all statement
	        pstmt.executeUpdate();
	        pstmt.close();
	        System.out.println("Thêm sản phẩm thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}		
	}


	private static void generateDB() {
		String sql = "select * from sanpham";
		String sql2 = "select * from taikhoan";
		
		try {
			conn = DbConnect.getconnect();
			PreparedStatement pre = conn.prepareStatement(sql);
			PreparedStatement pre2 = conn.prepareStatement(sql2);
			
			ResultSet res = pre.executeQuery();
			ResultSet res2 = pre2.executeQuery();
			while(res.next()) {
				String maSP = res.getString("masp");
				String tenSP = res.getString("tensp");
				long gia= res.getLong("gia");
				String loai = res.getString("loai");
				String mauSac = res.getString("mausac");
				SanPham sanPham= new SanPham(maSP, tenSP, gia, loai, mauSac);
				listSanPhams.add(sanPham);			
			}
			while(res2.next()) {
				String username = res2.getString("username");
				String password = res2.getString("password");
				TaiKhoan taiKhoan= new TaiKhoan(username, password);
				listTaiKhoans.add(taiKhoan);
			}
			
//			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class not found");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL err");
			e.printStackTrace();
		}
	}
}
