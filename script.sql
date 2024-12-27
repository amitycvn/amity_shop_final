USE [master]
GO
/****** Object:  Database [amity_shop_empty]    Script Date: 12/27/2024 8:24:03 PM ******/
CREATE DATABASE [amity_shop_empty]
GO
ALTER DATABASE [amity_shop_empty] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [amity_shop_empty].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [amity_shop_empty] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [amity_shop_empty] SET ANSI_NULLS OFF
GO
ALTER DATABASE [amity_shop_empty] SET ANSI_PADDING OFF
GO
ALTER DATABASE [amity_shop_empty] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [amity_shop_empty] SET ARITHABORT OFF
GO
ALTER DATABASE [amity_shop_empty] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [amity_shop_empty] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [amity_shop_empty] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [amity_shop_empty] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [amity_shop_empty] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [amity_shop_empty] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [amity_shop_empty] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [amity_shop_empty] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [amity_shop_empty] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [amity_shop_empty] SET  ENABLE_BROKER
GO
ALTER DATABASE [amity_shop_empty] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [amity_shop_empty] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [amity_shop_empty] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [amity_shop_empty] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [amity_shop_empty] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [amity_shop_empty] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [amity_shop_empty] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [amity_shop_empty] SET RECOVERY FULL
GO
ALTER DATABASE [amity_shop_empty] SET  MULTI_USER
GO
ALTER DATABASE [amity_shop_empty] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [amity_shop_empty] SET DB_CHAINING OFF
GO
ALTER DATABASE [amity_shop_empty] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [amity_shop_empty] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [amity_shop_empty] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [amity_shop_empty] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'amity_shop_empty', N'ON'
GO
ALTER DATABASE [amity_shop_empty] SET QUERY_STORE = OFF
GO
USE [amity_shop_empty]
GO
/****** Object:  Table [dbo].[chat_lieu]    Script Date: 12/27/2024 8:24:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chat_lieu](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[danh_muc]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[danh_muc](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[de_giay]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[de_giay](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[dia_chi]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[dia_chi](
    [id] [uniqueidentifier] NOT NULL,
    [id_nguoi_dung] [uniqueidentifier] NULL,
    [ten_nguoi_nhan] [nvarchar](50) NULL,
    [so_dien_thoai] [nvarchar](20) NULL,
    [ixa] [nvarchar](50) NULL,
    [ihuyen] [nvarchar](50) NULL,
    [ithanh_pho] [nvarchar](50) NULL,
    [dia_chi] [nvarchar](255) NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [trang_thai] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[dot_giam_gia]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[dot_giam_gia](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](max) NULL,
    [gia_tri] [money] NULL,
    [ngay_bat_dau] [datetime] NULL,
    [ngay_ket_thuc] [datetime] NULL,
    [loai] [bit] NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [deleted] [bit] NULL,
    [trang_thai] [nvarchar](255) NULL,
    [hinh_thuc] [nvarchar](100) NULL,
    [dieu_kien] [int] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
    CONSTRAINT [UQ_dot_giam_gia_ma] UNIQUE NONCLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[dot_giam_gia_spct]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[dot_giam_gia_spct](
    [id] [uniqueidentifier] NOT NULL,
    [id_spct] [uniqueidentifier] NULL,
    [id_dot_giam_gia] [uniqueidentifier] NULL,
    [trang_thai] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[gio_hang]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[gio_hang](
    [id] [uniqueidentifier] NOT NULL,
    [id_nguoi_dung] [uniqueidentifier] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[gio_hang_chi_tiet]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[gio_hang_chi_tiet](
    [id] [uniqueidentifier] NOT NULL,
    [id_gio_hang] [uniqueidentifier] NULL,
    [id_spct] [uniqueidentifier] NULL,
    [so_luong] [int] NULL,
    [thanh_tien] [money] NULL,
    [trang_thai] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[hang]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[hang](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[hinh_anh]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[hinh_anh](
    [id] [uniqueidentifier] NOT NULL,
    [id_sp] [uniqueidentifier] NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [url] [nvarchar](max) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[hoa_don]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[hoa_don](
    [id] [uniqueidentifier] NOT NULL,
    [id_nguoi_dung] [uniqueidentifier] NULL,
    [id_phieu_giam_gia] [uniqueidentifier] NULL,
    [id_dot_giam_gia] [uniqueidentifier] NULL,
    [ma] [nvarchar](50) NULL,
    [so_dien_thoai] [nvarchar](20) NULL,
    [dia_chi] [nvarchar](255) NULL,
    [gia_goc] [money] NULL,
    [gia_giam] [money] NULL,
    [tong_tien] [money] NULL,
    [email] [nvarchar](255) NULL,
    [loai_hoa_don] [nvarchar](50) NULL,
    [ghi_chu] [nvarchar](max) NULL,
    [qr_code] [nvarchar](255) NULL,
    [tien_van_chuyen] [money] NULL,
    [ngay_du_kien_nhan] [datetime] NULL,
    [ngay_nhan_hang] [datetime] NULL,
    [ngay_tra_hang] [datetime] NULL,
    [nhan_vien] [nvarchar](50) NULL,
    [ngay_mua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[hoa_don_chi_tiet]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[hoa_don_chi_tiet](
    [id] [uniqueidentifier] NOT NULL,
    [id_hoa_don] [uniqueidentifier] NULL,
    [id_spct] [uniqueidentifier] NULL,
    [so_luong] [int] NULL,
    [gia] [money] NULL,
    [gia_giam] [money] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [trang_thai] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[kich_thuoc]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[kich_thuoc](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[lich_su_hoa_don]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[lich_su_hoa_don](
    [id] [uniqueidentifier] NOT NULL,
    [id_hoa_don] [uniqueidentifier] NULL,
    [mo_ta] [nvarchar](max) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[lop_lot]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[lop_lot](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[mau_sac]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[mau_sac](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[nguoi_dung]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[nguoi_dung](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [email] [nvarchar](max) NULL,
    [sdt] [nvarchar](20) NULL,
    [mat_khau] [nvarchar](max) NULL,
    [ten] [nvarchar](50) NULL,
    [dia_chi] [nvarchar](255) NULL,
    [ngay_sinh] [datetime] NULL,
    [gioi_tinh] [nvarchar](50) NULL,
    [hinh_anh] [nvarchar](max) NULL,
    [cccd] [nvarchar](50) NULL,
    [diem] [int] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [chuc_vu] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    CONSTRAINT [PK__nguoi_du__3213E83FE44DCE53] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
    CONSTRAINT [UQ__nguoi_du__3213C8B601B5DCD6] UNIQUE NONCLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[phieu_giam_gia]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[phieu_giam_gia](
    [id] [uniqueidentifier] NOT NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](max) NULL,
    [loai] [bit] NULL,
    [gia_tri] [money] NULL,
    [giam_toi_da] [money] NULL,
    [muc_do] [nvarchar](50) NULL,
    [ngay_bat_dau] [datetime] NULL,
    [ngay_ket_thuc] [datetime] NULL,
    [so_luong] [int] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    [dieu_kien] [int] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
    CONSTRAINT [UQ_phieu_giam_gia_ma] UNIQUE NONCLUSTERED
(
[ma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[phieu_giam_gia_nguoi_dung]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[phieu_giam_gia_nguoi_dung](
    [id] [uniqueidentifier] NOT NULL,
    [id_nguoi_dung] [uniqueidentifier] NULL,
    [id_phieu_giam_gia] [uniqueidentifier] NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[phieu_giam_gia_san_pham]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[phieu_giam_gia_san_pham](
    [id] [uniqueidentifier] NOT NULL,
    [id_san_pham] [uniqueidentifier] NULL,
    [id_phieu_giam_gia] [uniqueidentifier] NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[san_pham]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[san_pham](
    [id] [uniqueidentifier] NOT NULL,
    [id_lop_lot] [uniqueidentifier] NULL,
    [id_chat_lieu] [uniqueidentifier] NULL,
    [ma] [nvarchar](50) NULL,
    [ten] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    [id_hang] [uniqueidentifier] NULL,
    [id_de_giay] [uniqueidentifier] NULL,
    [id_danh_muc] [uniqueidentifier] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[san_pham_chi_tiet]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[san_pham_chi_tiet](
    [id] [uniqueidentifier] NOT NULL,
    [id_san_pham] [uniqueidentifier] NULL,
    [id_mau_sac] [uniqueidentifier] NULL,
    [id_kich_thuoc] [uniqueidentifier] NULL,
    [ten] [nvarchar](50) NULL,
    [gia_ban] [money] NULL,
    [gia_nhap] [money] NULL,
    [qr_code] [nvarchar](max) NULL,
    [mo_ta] [nvarchar](255) NULL,
    [so_luong] [int] NULL,
    [so_luong_tra] [int] NULL,
    [hinh_anh] [nvarchar](max) NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [ghi_chu] [nvarchar](255) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[thanh_toan]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[thanh_toan](
    [id] [uniqueidentifier] NOT NULL,
    [id_hoa_don] [uniqueidentifier] NULL,
    [phuong_thuc] [nvarchar](50) NULL,
    [tien_mat] [money] NULL,
    [tien_chuyen_khoan] [money] NULL,
    [tong_tien] [money] NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [trang_thai] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [phuong_thuc_vnp] [nvarchar](max) NULL,
    [mo_ta] [nvarchar](255) NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[thong_bao]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[thong_bao](
    [id] [uniqueidentifier] NOT NULL,
    [id_hoa_don] [uniqueidentifier] NULL,
    [id_nguoi_dung] [uniqueidentifier] NULL,
    [loai] [nvarchar](50) NULL,
    [noi_dung] [nvarchar](max) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[tra_hang]    Script Date: 12/27/2024 8:24:03 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[tra_hang](
    [id] [uniqueidentifier] NOT NULL,
    [id_spct] [uniqueidentifier] NULL,
    [so_luong] [int] NULL,
    [ly_do] [nvarchar](max) NULL,
    [ngay_tao] [datetime] NULL,
    [ngay_sua] [datetime] NULL,
    [nguoi_tao] [nvarchar](50) NULL,
    [nguoi_sua] [nvarchar](50) NULL,
    [trang_thai] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[danh_muc] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[danh_muc] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[danh_muc] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[danh_muc] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[de_giay] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[de_giay] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[de_giay] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[de_giay] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[dia_chi] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[dia_chi] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[dia_chi] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[dia_chi] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT ((0)) FOR [gia_tri]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_bat_dau]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_ket_thuc]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT ((0)) FOR [loai]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT (N'Tất cả') FOR [hinh_thuc]
    GO
ALTER TABLE [dbo].[dot_giam_gia] ADD  DEFAULT ((-1)) FOR [dieu_kien]
    GO
ALTER TABLE [dbo].[dot_giam_gia_spct] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[dot_giam_gia_spct] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] ADD  DEFAULT ((0)) FOR [thanh_tien]
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[hang] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[hang] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[hang] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[hang] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[hinh_anh] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[hinh_anh] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[hinh_anh] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[hinh_anh] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT ((0)) FOR [gia_goc]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT ((0)) FOR [gia_giam]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT ((0)) FOR [tong_tien]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT ((0)) FOR [tien_van_chuyen]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_mua]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT ((0)) FOR [gia]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT ((0)) FOR [gia_giam]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[lich_su_hoa_don] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[lich_su_hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[lich_su_hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[lich_su_hoa_don] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[lop_lot] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[lop_lot] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[lop_lot] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[lop_lot] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[nguoi_dung] ADD  CONSTRAINT [DF__nguoi_dung__id__693CA210]  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[nguoi_dung] ADD  CONSTRAINT [DF__nguoi_dun__ngay___6A30C649]  DEFAULT (getdate()) FOR [ngay_sinh]
    GO
ALTER TABLE [dbo].[nguoi_dung] ADD  CONSTRAINT [DF__nguoi_dun__ngay___6B24EA82]  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[nguoi_dung] ADD  CONSTRAINT [DF__nguoi_dun__ngay___6C190EBB]  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[nguoi_dung] ADD  CONSTRAINT [DF__nguoi_dun__delet__6D0D32F4]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT ((0)) FOR [loai]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT ((0)) FOR [gia_tri]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT ((0)) FOR [giam_toi_da]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_bat_dau]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_ket_thuc]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[phieu_giam_gia] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_nguoi_dung] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_nguoi_dung] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_san_pham] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_san_pham] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  DEFAULT ((0)) FOR [gia_ban]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  DEFAULT ((0)) FOR [gia_nhap]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT ((0)) FOR [tien_mat]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT ((0)) FOR [tien_chuyen_khoan]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT ((0)) FOR [tong_tien]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[thanh_toan] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[thong_bao] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[thong_bao] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[thong_bao] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[thong_bao] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[tra_hang] ADD  DEFAULT (newid()) FOR [id]
    GO
ALTER TABLE [dbo].[tra_hang] ADD  DEFAULT (getdate()) FOR [ngay_tao]
    GO
ALTER TABLE [dbo].[tra_hang] ADD  DEFAULT (getdate()) FOR [ngay_sua]
    GO
ALTER TABLE [dbo].[tra_hang] ADD  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[dia_chi]  WITH CHECK ADD  CONSTRAINT [FK_dia_chi.id_nguoi_dung] FOREIGN KEY([id_nguoi_dung])
    REFERENCES [dbo].[nguoi_dung] ([id])
    GO
ALTER TABLE [dbo].[dia_chi] CHECK CONSTRAINT [FK_dia_chi.id_nguoi_dung]
    GO
ALTER TABLE [dbo].[dot_giam_gia_spct]  WITH CHECK ADD  CONSTRAINT [FK_dot_giam_gia_spct.id_dot_giam_gia] FOREIGN KEY([id_dot_giam_gia])
    REFERENCES [dbo].[dot_giam_gia] ([id])
    GO
ALTER TABLE [dbo].[dot_giam_gia_spct] CHECK CONSTRAINT [FK_dot_giam_gia_spct.id_dot_giam_gia]
    GO
ALTER TABLE [dbo].[dot_giam_gia_spct]  WITH CHECK ADD  CONSTRAINT [FK_dot_giam_gia_spct.id_spct] FOREIGN KEY([id_spct])
    REFERENCES [dbo].[san_pham_chi_tiet] ([id])
    GO
ALTER TABLE [dbo].[dot_giam_gia_spct] CHECK CONSTRAINT [FK_dot_giam_gia_spct.id_spct]
    GO
ALTER TABLE [dbo].[gio_hang]  WITH CHECK ADD  CONSTRAINT [FK_gio_hang.id_nguoi_dung] FOREIGN KEY([id_nguoi_dung])
    REFERENCES [dbo].[nguoi_dung] ([id])
    GO
ALTER TABLE [dbo].[gio_hang] CHECK CONSTRAINT [FK_gio_hang.id_nguoi_dung]
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_gio_hang_chi_tiet.id_gio_hang] FOREIGN KEY([id_gio_hang])
    REFERENCES [dbo].[gio_hang] ([id])
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] CHECK CONSTRAINT [FK_gio_hang_chi_tiet.id_gio_hang]
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_gio_hang_chi_tiet.id_spct] FOREIGN KEY([id_spct])
    REFERENCES [dbo].[san_pham_chi_tiet] ([id])
    GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] CHECK CONSTRAINT [FK_gio_hang_chi_tiet.id_spct]
    GO
ALTER TABLE [dbo].[hinh_anh]  WITH CHECK ADD  CONSTRAINT [FK_hinh_anh.id_sp] FOREIGN KEY([id_sp])
    REFERENCES [dbo].[san_pham] ([id])
    GO
ALTER TABLE [dbo].[hinh_anh] CHECK CONSTRAINT [FK_hinh_anh.id_sp]
    GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don.id_dot_giam_gia] FOREIGN KEY([id_dot_giam_gia])
    REFERENCES [dbo].[dot_giam_gia] ([id])
    GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don.id_dot_giam_gia]
    GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don.id_nguoi_dung] FOREIGN KEY([id_nguoi_dung])
    REFERENCES [dbo].[nguoi_dung] ([id])
    GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don.id_nguoi_dung]
    GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don.id_phieu_giam_gia] FOREIGN KEY([id_phieu_giam_gia])
    REFERENCES [dbo].[phieu_giam_gia] ([id])
    GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don.id_phieu_giam_gia]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_chi_tiet.id_hoa_don] FOREIGN KEY([id_hoa_don])
    REFERENCES [dbo].[hoa_don] ([id])
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] CHECK CONSTRAINT [FK_hoa_don_chi_tiet.id_hoa_don]
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_chi_tiet.id_spct] FOREIGN KEY([id_spct])
    REFERENCES [dbo].[san_pham_chi_tiet] ([id])
    GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] CHECK CONSTRAINT [FK_hoa_don_chi_tiet.id_spct]
    GO
ALTER TABLE [dbo].[lich_su_hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_lich_su_hoa_don.id_hoa_don] FOREIGN KEY([id_hoa_don])
    REFERENCES [dbo].[hoa_don] ([id])
    GO
ALTER TABLE [dbo].[lich_su_hoa_don] CHECK CONSTRAINT [FK_lich_su_hoa_don.id_hoa_don]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_nguoi_dung]  WITH CHECK ADD  CONSTRAINT [FK_phieu_giam_gia_nguoi_dung.id_nguoi_dung] FOREIGN KEY([id_nguoi_dung])
    REFERENCES [dbo].[nguoi_dung] ([id])
    GO
ALTER TABLE [dbo].[phieu_giam_gia_nguoi_dung] CHECK CONSTRAINT [FK_phieu_giam_gia_nguoi_dung.id_nguoi_dung]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_nguoi_dung]  WITH CHECK ADD  CONSTRAINT [FK_phieu_giam_gia_nguoi_dung.id_phieu_giam_gia] FOREIGN KEY([id_phieu_giam_gia])
    REFERENCES [dbo].[phieu_giam_gia] ([id])
    GO
ALTER TABLE [dbo].[phieu_giam_gia_nguoi_dung] CHECK CONSTRAINT [FK_phieu_giam_gia_nguoi_dung.id_phieu_giam_gia]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_phieu_giam_gia_san_pham.id_phieu_giam_gia] FOREIGN KEY([id_phieu_giam_gia])
    REFERENCES [dbo].[phieu_giam_gia] ([id])
    GO
ALTER TABLE [dbo].[phieu_giam_gia_san_pham] CHECK CONSTRAINT [FK_phieu_giam_gia_san_pham.id_phieu_giam_gia]
    GO
ALTER TABLE [dbo].[phieu_giam_gia_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_phieu_giam_gia_san_pham.id_san_pham] FOREIGN KEY([id_san_pham])
    REFERENCES [dbo].[san_pham] ([id])
    GO
ALTER TABLE [dbo].[phieu_giam_gia_san_pham] CHECK CONSTRAINT [FK_phieu_giam_gia_san_pham.id_san_pham]
    GO
ALTER TABLE [dbo].[san_pham]  WITH CHECK ADD  CONSTRAINT [FK_san_pham.id_chat_lieu] FOREIGN KEY([id_chat_lieu])
    REFERENCES [dbo].[chat_lieu] ([id])
    GO
ALTER TABLE [dbo].[san_pham] CHECK CONSTRAINT [FK_san_pham.id_chat_lieu]
    GO
ALTER TABLE [dbo].[san_pham]  WITH CHECK ADD  CONSTRAINT [FK_san_pham.id_lop_lot] FOREIGN KEY([id_lop_lot])
    REFERENCES [dbo].[lop_lot] ([id])
    GO
ALTER TABLE [dbo].[san_pham] CHECK CONSTRAINT [FK_san_pham.id_lop_lot]
    GO
ALTER TABLE [dbo].[san_pham]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_danh_muc] FOREIGN KEY([id_danh_muc])
    REFERENCES [dbo].[danh_muc] ([id])
    GO
ALTER TABLE [dbo].[san_pham] CHECK CONSTRAINT [FK_san_pham_danh_muc]
    GO
ALTER TABLE [dbo].[san_pham]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_de_giay] FOREIGN KEY([id_de_giay])
    REFERENCES [dbo].[de_giay] ([id])
    GO
ALTER TABLE [dbo].[san_pham] CHECK CONSTRAINT [FK_san_pham_de_giay]
    GO
ALTER TABLE [dbo].[san_pham]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_hang] FOREIGN KEY([id_hang])
    REFERENCES [dbo].[hang] ([id])
    GO
ALTER TABLE [dbo].[san_pham] CHECK CONSTRAINT [FK_san_pham_hang]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet.id_kich_thuoc] FOREIGN KEY([id_kich_thuoc])
    REFERENCES [dbo].[kich_thuoc] ([id])
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet.id_kich_thuoc]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet.id_mau_sac] FOREIGN KEY([id_mau_sac])
    REFERENCES [dbo].[mau_sac] ([id])
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet.id_mau_sac]
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet.id_san_pham] FOREIGN KEY([id_san_pham])
    REFERENCES [dbo].[san_pham] ([id])
    GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet.id_san_pham]
    GO
ALTER TABLE [dbo].[thanh_toan]  WITH CHECK ADD  CONSTRAINT [FK_thanh_toan.id_hoa_don] FOREIGN KEY([id_hoa_don])
    REFERENCES [dbo].[hoa_don] ([id])
    GO
ALTER TABLE [dbo].[thanh_toan] CHECK CONSTRAINT [FK_thanh_toan.id_hoa_don]
    GO
ALTER TABLE [dbo].[thong_bao]  WITH CHECK ADD  CONSTRAINT [FK_thong_bao.id_hoa_don] FOREIGN KEY([id_hoa_don])
    REFERENCES [dbo].[hoa_don] ([id])
    GO
ALTER TABLE [dbo].[thong_bao] CHECK CONSTRAINT [FK_thong_bao.id_hoa_don]
    GO
ALTER TABLE [dbo].[thong_bao]  WITH CHECK ADD  CONSTRAINT [FK_thong_bao.id_nguoi_dung] FOREIGN KEY([id_nguoi_dung])
    REFERENCES [dbo].[nguoi_dung] ([id])
    GO
ALTER TABLE [dbo].[thong_bao] CHECK CONSTRAINT [FK_thong_bao.id_nguoi_dung]
    GO
ALTER TABLE [dbo].[tra_hang]  WITH CHECK ADD  CONSTRAINT [FK_tra_hang.id_spct] FOREIGN KEY([id_spct])
    REFERENCES [dbo].[san_pham_chi_tiet] ([id])
    GO
ALTER TABLE [dbo].[tra_hang] CHECK CONSTRAINT [FK_tra_hang.id_spct]
    GO
    USE [master]
    GO
ALTER DATABASE [amity_shop_empty] SET  READ_WRITE 
GO
