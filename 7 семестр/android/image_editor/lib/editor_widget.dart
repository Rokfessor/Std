import 'dart:developer';
import 'dart:io';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:image_editor/settings_widget.dart';
import 'package:image_gallery_saver/image_gallery_saver.dart';
import 'package:image_picker/image_picker.dart';
import 'package:http/http.dart' as http;
import 'package:loading_animation_widget/loading_animation_widget.dart';

class ImageEditorWidget extends StatefulWidget {
  const ImageEditorWidget({super.key});

  @override
  State<StatefulWidget> createState() => _ImageEditorWidgetState();
}

class _ImageEditorWidgetState extends State<ImageEditorWidget> {
  XFile? file;
  Image? editedImage;
  ImagePicker picker = ImagePicker();
  bool filePicked = false;
  bool showSettings = false;
  bool isLoading = false;
  Settings settings = Settings.getDefault();
  Color saveBtnColor = Colors.black12;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        alignment: Alignment.bottomCenter,
        children: [
          _imageContainer(),
          SettingsWidget(isActive: showSettings, settings: settings),

        ],
      ),
      extendBody: true,
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      floatingActionButton: FloatingActionButton(
        onPressed: () => _pickImage(ImageSource.camera),
        child: _takePictureIcon(),
      ),
      bottomNavigationBar: BottomAppBar(
        shape: const CircularNotchedRectangle(),
        child: IconTheme(
          data: IconThemeData(color: Theme.of(context).colorScheme.onPrimary),
          child: Padding(
            padding: const EdgeInsets.all(12.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                PopupMenuButton<String>(
                  enabled: !isLoading,
                  onSelected: (String type) {
                    setState(() => settings.effectType = type);
                  },
                  initialValue: settings.effectType,
                  icon: const Icon(Icons.star, color: Colors.black12),
                  itemBuilder: (BuildContext context) =>
                      <PopupMenuEntry<String>>[
                    const PopupMenuItem<String>(
                      value: EffectType.seamCarving,
                      child: Text(EffectType.seamCarving),
                    ),
                    const PopupMenuItem<String>(
                      value: EffectType.monochrome,
                      child: Text(EffectType.monochrome),
                    ),
                  ],
                ),
                IconButton(
                    onPressed: _openSettings,
                    icon: const Icon(Icons.settings, color: Colors.black12)),
                IconButton(
                    padding: const EdgeInsets.only(top: 20),
                    onPressed: () => _pickImage(ImageSource.gallery),
                    icon:
                        const Icon(Icons.image_search, color: Colors.black12)),
                IconButton(
                    onPressed: () {
                      if (isLoading) {
                        return;
                      }

                      if (editedImage != null) {
                        ImageGallerySaver.saveImage(base64Decode(settings.image));
                        setState(() {
                          saveBtnColor = Colors.green;
                        });
                      }
                    },
                    icon: Icon(Icons.save, color: saveBtnColor)),
                IconButton(
                    onPressed: _processImage,
                    icon: Icon(Icons.accessible_forward_outlined,
                        color: _processImageButtonColor())),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void _pickImage(ImageSource source) {
    if (isLoading) {
      return;
    }

    picker.pickImage(source: source).then((value) => setState(() {
          if (value != null) {
            editedImage = null;
            file = value;
            filePicked = file != null;
            file?.readAsBytes().then((value) =>
            {
              settings.image = base64Encode(value.toList())
            });
            log(filePicked.toString());
            log(file!.path);
          }
        }));
  }

  Color _processImageButtonColor() {
    return filePicked ? Colors.green : Colors.black12;
  }

  void _processImage() {
    if (!filePicked || isLoading) return;

    setState(() {
      isLoading = true;
    });

    final uri = Uri.http('192.168.125.50:8080', 'api/process_image', {'effect': settings.effectType});
    http.post(uri, body: settings.toString(), headers: {'Content-type': 'application/json'}).then((value) {
      String base64Img = jsonDecode(value.body)['image'].split(',')[1];
      setState(() {
        settings.image = base64Img;
        editedImage = Image.memory(base64Decode(base64Img));
        saveBtnColor = Colors.black12;
        isLoading = false;
      });
    });
  }

  Positioned _imageContainer() {
    log("Drawing img container: $filePicked");
    if (!filePicked) {
      return const Positioned(
        child: Center(child: Text("Pick image")),
      );
    }
    
    if (editedImage != null) {
      return Positioned(
        child: Center(
          child: editedImage,
        ),
      );
    }
    
    return Positioned(
      child: Center(
        child: Image.file(File(file!.path)),
      ),
    );
  }

  void _openSettings() {
    if (isLoading) {
      return;
    }

    setState(() => showSettings = !showSettings);
  }
  
  Center _takePictureIcon() {
    if (isLoading) {
      return Center(
        child: LoadingAnimationWidget.threeRotatingDots(color: Colors.white, size: 30)
      );
    }

    return const Center(
        child: Icon(Icons.camera_alt_outlined)
    );
  }
}
